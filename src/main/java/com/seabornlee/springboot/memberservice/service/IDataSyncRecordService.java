package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;

public interface IDataSyncRecordService {

    public PageInfo<DataSyncRecord> getListByPage(int page, int size);

    public void saveRecord(DataSyncRecord record);

    public void updateRecord(DataSyncRecord record);

    public void deleteRecord(DataSyncRecord record);
}
