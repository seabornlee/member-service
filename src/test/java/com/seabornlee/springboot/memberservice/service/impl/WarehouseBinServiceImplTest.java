package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.mapper.WarehouseBinMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class WarehouseBinServiceImplTest {

    @Mock
    private WarehouseBinMapper warehouseBinMapper;
    @InjectMocks
    private WarehouseBinServiceImpl warehouseBinService;

    @Test
    public void getListByPage() {
        PageInfo<WarehouseBin> pageInfo = warehouseBinService.getListByPage(new WarehouseBin(),1,10);

        assertThat(pageInfo.getList()).isNull();
    }

    @Test
    public void existWarehouseBin() {
        assertThat(warehouseBinService.existWarehouseBin(new WarehouseBin())).isFalse();
    }

    @Test
    public void saveWarehouseBin() {
        WarehouseBinServiceImpl mock = spy(warehouseBinService);
        mock.saveWarehouseBin(new WarehouseBin());
        verify(spy(mock), times(1));
    }

    @Test
    public void saveOrUpdate() {
        WarehouseBinServiceImpl mock = spy(warehouseBinService);
        mock.saveOrUpdate(new WarehouseBin());
        verify(spy(mock), times(1));
    }

    @Test
    public void updateWarehouseBin() {
        WarehouseBinServiceImpl mock = spy(warehouseBinService);
        mock.updateWarehouseBin(new WarehouseBin());
        verify(spy(mock), times(1));
    }

    @Test
    public void deleteWarehouseBin() {
        WarehouseBinServiceImpl mock = spy(warehouseBinService);
        mock.deleteWarehouseBin(new WarehouseBin());
        verify(spy(mock), times(1));
    }
}