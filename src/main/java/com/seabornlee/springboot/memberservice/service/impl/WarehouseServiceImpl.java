package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.domain.Warehouse;
import com.seabornlee.springboot.memberservice.mapper.WarehouseMapper;
import com.seabornlee.springboot.memberservice.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public PageInfo<Warehouse> getListByPage(Warehouse query, int page, int size) {
        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        PageInfo<Warehouse> pageInfo = new PageInfo<>(warehouseMapper.selectByExample(query));

        return pageInfo;
    }

    @Override
    public boolean existWarehouse(Warehouse warehouse) {
        Warehouse warehouseFromDb = warehouseMapper.selectOneByExample(warehouse);
        return null!=warehouseFromDb;
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        warehouseMapper.insert(warehouse);
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseMapper.updateByPrimaryKey(warehouse);
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseMapper.deleteByPrimaryKey(warehouse);
    }
}
