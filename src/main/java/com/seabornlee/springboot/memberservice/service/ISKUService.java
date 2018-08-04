package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.SKU;

public interface ISKUService {

    public PageInfo<SKU> getListByPage(SKU query, int page, int size);

    public boolean existSKU(SKU sku);

    public void saveSKU(SKU sku);

    public void saveOrUpdate(SKU sku);

    public void updateSKU(SKU sku);

    public void deleteSKU(SKU sku);
}
