package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.mapper.WarehouseBinMapper;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseBinServiceImpl implements IWarehouseBinService {

    @Autowired
    private WarehouseBinMapper warehouseBinMapper;


    @Override
    public PageInfo<WarehouseBin> getListByPage(WarehouseBin query, int page, int size) {
        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        PageInfo<WarehouseBin> pageInfo = new PageInfo<>(warehouseBinMapper.selectByExample(query));

        return pageInfo;
    }

    @Override
    public Boolean existWarehouseBin(WarehouseBin warehouseBin) {
        WarehouseBin binFromDb = warehouseBinMapper.selectOneByExample(warehouseBin);
        return null!=binFromDb;
    }

    @Override
    public void saveWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.insert(warehouseBin);
    }

    @Override
    public void saveOrUpdate(WarehouseBin warehouseBin) {
        WarehouseBin query = new WarehouseBin();
        query.setBinNo(warehouseBin.getBinNo())
                .setDataSource(warehouseBin.getDataSource())
                .setWarehouseId(warehouseBin.getWarehouseId());
        if(existWarehouseBin(query)){
            if(null!=warehouseBin.getId()){
                updateWarehouseBin(warehouseBin);
            }
        }else {
            saveWarehouseBin(warehouseBin);
        }

    }

    @Override
    public void updateWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.updateByPrimaryKey(warehouseBin);
    }

    @Override
    public void deleteWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.deleteByPrimaryKey(warehouseBin);
    }
}
