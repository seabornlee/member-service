package com.seabornlee.springboot.memberservice.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.service.AbstractDataSyncService;
import com.seabornlee.springboot.memberservice.service.ISKUService;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import com.seabornlee.springboot.memberservice.util.enumeration.DataSourceEnum;
import com.seabornlee.springboot.memberservice.util.enumeration.DataType;
import com.seabornlee.springboot.memberservice.util.spider.ESpiritSpider;
import com.seabornlee.springboot.memberservice.util.spider.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ESpiritDataSyncService extends AbstractDataSyncService {

    private final String SKU_ALL_DATA_URL = "https://hz3.ejlerp.com/skuQuery/list";
    private final String SKU_INCREMENT_DATA_URL = "https://hz1.ejlerp.com/skuQuery/quickFilter";

    private final String WAREHOUSE_BIN_ALL_DATA_URL = "https://hz1.ejlerp.com/warehouse_bin/list";
    private final String WAREHOUSE_BIN_INCREMENT_DATA_URL = "https://hz1.ejlerp.com/warehouse_bin/querybyctgr";

    private String username = "potato";
    private String password = "potato-plan";
    private String loginUrl = "http://www.runscm.com/user_login";

    @Autowired
    private ISKUService skuService;
    @Autowired
    private IWarehouseBinService warehouseBinService;

    @Override
    protected void doSync(DataType type) {
        doSync(type,null,null);
    }

    @Override
    protected void doSync(DataType type, Date start, Date end) {

        if(null==start){

            switch (type){
                case SKU:
                    SKUProcessor processor = new SKUProcessor();
                    processSKUList(processor.getData());
                    break;
                case WAREHOUSE_BIN:
                    WarehouseBinPorcessor wbProcessor = new WarehouseBinPorcessor();
                    processWarehouseBins(wbProcessor.getData());
                    break;
                default:
                    break;
            }

        }else{

        }

    }

    @Override
    protected DataSourceEnum getDataSource() {
        return DataSourceEnum.ESpirit;
    }

    private Spider doGetSpider(){
        return new ESpiritSpider(username,password,loginUrl);
    }

    public void processSKUList(List<SKU> list){

        if(null==list||list.isEmpty()){
            return;
        }

        for(SKU sku : list){
            skuService.saveOrUpdate(sku);
        }

    }

    public void processWarehouseBins(List<WarehouseBin> list){

        if(null==list||list.isEmpty()){
            return;
        }
        for(WarehouseBin bin : list){
            warehouseBinService.saveOrUpdate(bin);
        }
    }

    class SKUProcessor extends AbstractDataProcessor<SKU>{

        private boolean isIncrement;
        public SKUProcessor() {
            super(SKU_ALL_DATA_URL, DataType.SKU, false, null);
            isIncrement = false;
        }

        public SKUProcessor(Date start, Date end){
            super(SKU_INCREMENT_DATA_URL, DataType.SKU, true, null);
            //params = new HashMap<String,Object>();
            //params.put("")
            isIncrement = true;
        }

        @Override
        public List<SKU> process(JSONArray list) {
            if(null==list||list.isEmpty()){
                return null;
            }
            List<SKU> result = new ArrayList<>(list.size());
            for(int i=0;i<list.size();i++){
                JSONObject one = list.getJSONObject(i);
                SKU sku = new SKU();
                sku.setBarCode(one.getString("bar_code"));
                sku.setDataSource(DataSourceEnum.ESpirit.getValue());
                sku.setPic(one.getString("pic"));
                sku.setSkuNo(one.getString("sku_no"));
                sku.setTenantId(one.getInteger("tenant_id"));
                result.add(sku);
            }
            return result;
        }

        @Override
        protected Spider getSpider() {
            return doGetSpider();
        }

        @Override
        protected JSONArray getDataList() {
            Object data = spider.getData(url,params,isPost,true);
            if(isIncrement){

            }else{
                return (JSONArray) data;
            }
            return null;
        }
    }

    class WarehouseBinPorcessor extends AbstractDataProcessor<WarehouseBin>{

        private boolean isIncrement;
        public WarehouseBinPorcessor() {
            super(WAREHOUSE_BIN_ALL_DATA_URL, DataType.WAREHOUSE_BIN, false, null);
            isIncrement = false;
        }

        public WarehouseBinPorcessor(Date start, Date end){
            super(WAREHOUSE_BIN_INCREMENT_DATA_URL, DataType.WAREHOUSE_BIN, true, null);
            //params = new HashMap<String,Object>();
            //params.put("")
            isIncrement = true;
        }

        @Override
        public List<WarehouseBin> process(JSONArray list) {
            if(null==list||list.isEmpty()){
                return null;
            }
            List<WarehouseBin> result = new ArrayList<>(list.size());
            for(int i=0;i<list.size();i++){
                JSONObject one = list.getJSONObject(i);
                WarehouseBin bin = new WarehouseBin();
                bin.setBinNo(one.getString("warehouse_bin_no"));
                bin.setName(one.getString("warehouse_name"));
                bin.setAreaName(one.getString("warehouse_area_name"));
                bin.setAreaType(one.getString("warehouse_area_type"));
                bin.setShelfNo(one.getString("warehouse_shelf_name"));
                bin.setEnabled(one.getBoolean("is_enabled_code"));
                bin.setTmp(one.getBoolean("is_tmp_code"));
                bin.setDataSource(DataSourceEnum.ESpirit.getValue());
                bin.setCapacity(one.getInteger("capacity"));
                result.add(bin);
            }
            return result;
        }

        @Override
        protected Spider getSpider() {
            return doGetSpider();
        }

        @Override
        protected JSONArray getDataList() {
            Object data = spider.getData(url,params,isPost,true);
            if(isIncrement){

            }else{
                return (JSONArray) data;
            }
            return null;
        }
    }
}
