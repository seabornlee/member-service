package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.util.enumeration.DataType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.ExecutorService;


public abstract class AbstractDataSyncService implements IDataSyncService{

    @Autowired
    protected ExecutorService executorService;
    @Autowired
    protected IDataSyncRecordService dataSyncRecordService;

    @Override
    public void sync(DataType type) {
        sync(type, null, null);
    }

    @Override
    public void sync(DataType type, Date start, Date end) {

        //

    }

    protected abstract void doSync(DataType type);
    protected abstract void doSync(DataType type, Date start, Date end);
}
