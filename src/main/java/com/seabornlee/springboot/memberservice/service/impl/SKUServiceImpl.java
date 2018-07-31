package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.mapper.SKUMapper;
import com.seabornlee.springboot.memberservice.service.ISKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SKUServiceImpl implements ISKUService {

    @Autowired
    private SKUMapper skuMapper;

    @Override
    public PageInfo<SKU> getListByPage(SKU query, int page, int size) {
        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        PageInfo<SKU> pageInfo = new PageInfo<>(skuMapper.selectByExample(query));

        return pageInfo;
    }

    @Override
    public boolean existSKU(SKU sku) {
        SKU skuFromDb = skuMapper.selectOneByExample(sku);
        return null!=skuFromDb;
    }

    @Override
    public void saveSKU(SKU sku) {
        skuMapper.insert(sku);
    }

    @Override
    public void updateSKU(SKU sku) {
        skuMapper.updateByPrimaryKey(sku);
    }

    @Override
    public void deleteSKU(SKU sku) {
        skuMapper.deleteByPrimaryKey(sku);
    }
}
