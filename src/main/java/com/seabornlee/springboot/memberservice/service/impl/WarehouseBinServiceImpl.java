package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.mapper.WarehouseBinMapper;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseBinServiceImpl implements IWarehouseBinService {

    @Autowired
    private WarehouseBinMapper warehouseBinMapper;


    @Override
    public PageInfo<WarehouseBin> getListByPage(WarehouseBin query, int page, int size) {
        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);
        List<WarehouseBin> list = null==query?warehouseBinMapper.selectAll():warehouseBinMapper.select(query);
        PageInfo<WarehouseBin> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public Boolean existWarehouseBin(WarehouseBin warehouseBin) {
        WarehouseBin binFromDb = warehouseBinMapper.selectOne(warehouseBin);
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
                .setTenantId(warehouseBin.getTenantId());
        if(existWarehouseBin(query)){
            WarehouseBin binFromDb = warehouseBinMapper.selectOne(query);
            warehouseBin.setId(binFromDb.getId());
            if(null!=warehouseBin.getId()){
                updateWarehouseBin(warehouseBin);
            }
        }else {
            saveWarehouseBin(warehouseBin);
        }

    }

    @Override
    public void updateWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.updateByPrimaryKeySelective(warehouseBin);
    }

    @Override
    public void deleteWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.deleteByPrimaryKey(warehouseBin);
    }
}
