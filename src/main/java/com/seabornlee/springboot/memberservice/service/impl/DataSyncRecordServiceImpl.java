package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.mapper.DataSyncRecordMapper;
import com.seabornlee.springboot.memberservice.service.IDataSyncRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSyncRecordServiceImpl implements IDataSyncRecordService {

    @Autowired
    private DataSyncRecordMapper dataSyncRecordMapper;

    @Override
    public PageInfo<DataSyncRecord> getListByPage(int page, int size) {
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 10;
        PageHelper.startPage(page, size, true);

        PageInfo<DataSyncRecord> pageInfo = new PageInfo<>(dataSyncRecordMapper.selectAll());

        return pageInfo;
    }

    @Override
    public PageInfo<DataSyncRecord> getListByPage(DataSyncRecord record, int page, int size) {
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 10;
        PageHelper.startPage(page, size, true);
        List<DataSyncRecord> list = null == record ? dataSyncRecordMapper.selectAll() : dataSyncRecordMapper.select(record);
        PageInfo<DataSyncRecord> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public void saveRecord(DataSyncRecord record) {

        dataSyncRecordMapper.insert(record);
    }

    @Override
    public void updateRecord(DataSyncRecord record) {

        dataSyncRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteRecord(DataSyncRecord record) {

        dataSyncRecordMapper.deleteByPrimaryKey(record);
    }
}
