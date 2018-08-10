package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;

public interface IWarehouseBinService {

    public PageInfo<WarehouseBin> getListByPage(WarehouseBin query, int page, int size);

    public Boolean existWarehouseBin(WarehouseBin warehouseBin);

    public void saveWarehouseBin(WarehouseBin warehouseBin);

    public void saveOrUpdate(WarehouseBin warehouseBin);

    public void updateWarehouseBin(WarehouseBin warehouseBin);

    public void deleteWarehouseBin(WarehouseBin warehouseBin);
}
