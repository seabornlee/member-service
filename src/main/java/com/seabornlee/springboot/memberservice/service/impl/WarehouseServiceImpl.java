package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.Warehouse;
import com.seabornlee.springboot.memberservice.mapper.WarehouseMapper;
import com.seabornlee.springboot.memberservice.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public PageInfo<Warehouse> getListByPage(Warehouse query, int page, int size) {
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 10;
        PageHelper.startPage(page, size, true);
        List<Warehouse> list = null == query ? warehouseMapper.selectAll() : warehouseMapper.select(query);
        PageInfo<Warehouse> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public boolean existWarehouse(Warehouse warehouse) {
        Warehouse warehouseFromDb = warehouseMapper.selectOne(warehouse);
        return null != warehouseFromDb;
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        warehouseMapper.insert(warehouse);
    }

    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        Warehouse query = new Warehouse();
        query.setWarehouseNo(warehouse.getWarehouseNo())
                .setDataSource(warehouse.getDataSource())
                .setTenantId(warehouse.getTenantId());

        if (existWarehouse(query)) {
            Warehouse warehouseFromDb = warehouseMapper.selectOne(query);
            warehouse.setId(warehouseFromDb.getId());
            if (null != warehouse.getId()) {
                updateWarehouse(warehouse);
            }
        } else {
            saveWarehouse(warehouse);
        }
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        warehouseMapper.deleteByPrimaryKey(warehouse);
    }
}
