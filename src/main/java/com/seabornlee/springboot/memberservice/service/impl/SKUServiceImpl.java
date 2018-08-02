package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.mapper.SKUMapper;
import com.seabornlee.springboot.memberservice.service.ISKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SKUServiceImpl implements ISKUService {

    @Autowired
    private SKUMapper skuMapper;

    @Override
    public PageInfo<SKU> getListByPage(SKU query, int page, int size) {
        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        List<SKU> list = null==query?skuMapper.selectAll():skuMapper.select(query);
        PageInfo<SKU> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public boolean existSKU(SKU sku) {
        SKU skuFromDb = skuMapper.selectOne(sku);
        return null!=skuFromDb;
    }

    @Override
    public void saveSKU(SKU sku) {
        skuMapper.insert(sku);
    }

    @Override
    public void saveOrUpdate(SKU sku) {

        SKU query = new SKU();
        query.setSkuNo(sku.getSkuNo()).setTenantId(sku.getTenantId()).setDataSource(sku.getDataSource());

        if(existSKU(query)){
            SKU skuFromDb = skuMapper.selectOne(query);
            sku.setId(skuFromDb.getId());
            if(null!=sku.getId()){
                updateSKU(sku);
            }

        }else if(!existSKU(query)){
            saveSKU(sku);
        }

    }

    @Override
    public void updateSKU(SKU sku) {
        skuMapper.updateByPrimaryKeySelective(sku);
    }

    @Override
    public void deleteSKU(SKU sku) {
        skuMapper.deleteByPrimaryKey(sku);
    }
}
