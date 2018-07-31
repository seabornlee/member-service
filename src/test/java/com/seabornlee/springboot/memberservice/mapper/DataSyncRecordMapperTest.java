package com.seabornlee.springboot.memberservice.mapper;

import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
public class DataSyncRecordMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private DataSyncRecordMapper dataSyncRecordMapper;

    @Test
    public void testSelectAll(){

        List<DataSyncRecord> list = dataSyncRecordMapper.selectAll();

        logger.info("list size: " + (list==null?0:list.size()));

        assertThat(list).isNull();
    }
}