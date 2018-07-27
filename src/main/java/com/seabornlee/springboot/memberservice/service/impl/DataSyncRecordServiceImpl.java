package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.mapper.DataSyncRecordMapper;
import com.seabornlee.springboot.memberservice.service.IDataSyncRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSyncRecordServiceImpl implements IDataSyncRecordService {

    @Autowired
    private DataSyncRecordMapper dataSyncRecordMapper;

    @Override
    public PageInfo<DataSyncRecord> getListByPage(int page, int size) {

        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        PageInfo<DataSyncRecord> pageInfo = new PageInfo<>(dataSyncRecordMapper.selectAll());

        return pageInfo;
    }

    @Override
    public void saveRecord(DataSyncRecord record) {
        dataSyncRecordMapper.insert(record);
    }

    @Override
    public void updateRecord(DataSyncRecord record) {
        dataSyncRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteRecord(DataSyncRecord record) {
        dataSyncRecordMapper.delete(record);
    }
}
