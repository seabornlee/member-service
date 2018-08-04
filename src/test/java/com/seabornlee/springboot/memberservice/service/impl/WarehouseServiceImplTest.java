package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.Warehouse;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.mapper.WarehouseMapper;
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
public class WarehouseServiceImplTest {

    @Mock
    private WarehouseMapper warehouseMapper;
    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @Test
    public void getListByPage() {
        PageInfo<Warehouse> pageInfo = warehouseService.getListByPage(new Warehouse(),1,10);

        assertThat(pageInfo.getList()).isNull();
    }

    @Test
    public void existWarehouse() {
        assertThat(warehouseService.existWarehouse(new Warehouse())).isFalse();
    }

    @Test
    public void saveWarehouse() {
        WarehouseServiceImpl mock = spy(warehouseService);
        mock.saveWarehouse(new Warehouse());
        verify(spy(mock), times(1));
    }

    @Test
    public void saveOrUpdate() {
        WarehouseServiceImpl mock = spy(warehouseService);
        mock.saveOrUpdate(new Warehouse());
        verify(spy(mock), times(1));
    }

    @Test
    public void updateWarehouse() {
        WarehouseServiceImpl mock = spy(warehouseService);
        mock.updateWarehouse(new Warehouse());
        verify(spy(mock), times(1));
    }

    @Test
    public void deleteWarehouse() {
        WarehouseServiceImpl mock = spy(warehouseService);
        mock.deleteWarehouse(new Warehouse());
        verify(spy(mock), times(1));
    }
}