package com.seabornlee.springboot.memberservice.service;

import com.alibaba.fastjson.JSONArray;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.util.enumeration.DataSourceEnum;
import com.seabornlee.springboot.memberservice.util.enumeration.DataSyncStatus;
import com.seabornlee.springboot.memberservice.util.enumeration.DataType;
import com.seabornlee.springboot.memberservice.util.spider.Spider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;


public abstract class AbstractDataSyncService implements IDataSyncService{

    @Autowired
    protected ExecutorService executorService;
    @Autowired
    protected IDataSyncRecordService dataSyncRecordService;

    @Override
    public DataSyncRecord sync(HttpServletRequest request, DataType type) {
        return sync(request, type, null, null);
    }

    @Override
    public DataSyncRecord sync(HttpServletRequest request, DataType type, Date start, Date end) {

        //
        final DataSyncRecord record = makeRecord(request,type);
        dataSyncRecordService.saveRecord(record);

        executorService.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    if(null==start){//无开始时间全量取
                        doSync(type);
                    }else{
                        Date endTime = null==end?new Date():end;
                        doSync(type, start, endTime);
                    }
                    // sync successfully
                    DataSyncRecord r = new DataSyncRecord();
                    r.setId(record.getId());
                    r.setSuccess(true);
                    r.setStatus(DataSyncStatus.COMPLETED.getValue());
                    dataSyncRecordService.updateRecord(r);
                } catch (Exception e) {
                    DataSyncRecord r = new DataSyncRecord();
                    r.setId(record.getId());
                    r.setSuccess(false);
                    r.setStatus(DataSyncStatus.COMPLETED.getValue());
                    dataSyncRecordService.updateRecord(r);
                    e.printStackTrace();
                    throw e;
                }

            }
        });

        return record;

    }

    private DataSyncRecord makeRecord(HttpServletRequest request, DataType type){

        DataSyncRecord record = new DataSyncRecord();
        record.setDataSource(getDataSource().getValue());
        record.setMethod(request.getMethod());
        record.setOpTime(new Date().getTime());
        record.setStatus(DataSyncStatus.SUBMITTED.getValue());
        record.setUrl(request.getRequestURL().toString());
        record.setSuccess(false);
        //record.setOperator(request.getSession().getAttribute(Constants.SESSION_USER_NAME_KEY).toString());

        return record;
    }

    protected abstract void doSync(DataType type);
    protected abstract void doSync(DataType type, Date start, Date end);
    protected abstract DataSourceEnum getDataSource();

    interface DataProcessor<T>{
        public List<T> process(JSONArray list);
    }

    public abstract class AbstractDataProcessor<T> implements DataProcessor<T>{
        protected String url;
        protected DataType type;
        protected boolean isPost;
        protected Map<String,String> params;
        protected Spider spider;

        public AbstractDataProcessor(String url, DataType type, boolean isPost, Map<String, String> params) {
            this.url = url;
            this.type = type;
            this.isPost = isPost;
            this.params = params;
            this.spider = getSpider();
        }

        protected abstract Spider getSpider();
        protected abstract JSONArray getDataList();

        public List<T> getData(){
            return process(getDataList());
        }
    }
}
