package com.seabornlee.springboot.memberservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.service.IDataSyncRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DataSyncRecordServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private IDataSyncRecordService dataSyncRecordService;

    @Test
    public void getListByPage() {

        PageInfo<DataSyncRecord> page = dataSyncRecordService.getListByPage(1,10);

        logger.info(JSON.toJSONString(page));

        assertThat(page).isNull();
    }
}