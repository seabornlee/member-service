package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.util.enumeration.DataType;

import java.util.Date;

public interface IDataSyncService {

    /**
     * 全量
     * @param type 数据表
     * */
    public void sync(DataType type);

    /**
     * 增量
     * @param type 数据表
     * */
    public void sync(DataType type, Date start, Date end);
}
