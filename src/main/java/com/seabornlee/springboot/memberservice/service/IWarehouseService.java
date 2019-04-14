package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.Warehouse;

public interface IWarehouseService {

    public PageInfo<Warehouse> getListByPage(Warehouse query, int page, int size);

    public boolean existWarehouse(Warehouse warehouse);

    public void saveWarehouse(Warehouse warehouse);

    public void saveOrUpdate(Warehouse warehouse);

    public void updateWarehouse(Warehouse warehouse);

    public void deleteWarehouse(Warehouse warehouse);
}
