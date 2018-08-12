package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.potato.cloud.common.dao.PageResult;
import com.seabornlee.springboot.memberservice.api.model.InboundRequestModel;
import com.seabornlee.springboot.memberservice.domain.Inventory;
import com.seabornlee.springboot.memberservice.mapper.InventoryMapper;
import com.seabornlee.springboot.memberservice.service.InventoryService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by sh on 2018/8/11.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    public PageResult<Inventory> query(Inventory.QueryCondition params) {
        PageResult<Inventory> result = new PageResult<Inventory>();

        Condition cond = new Condition(Inventory.class);
        Example.Criteria criteria = cond.createCriteria();

        if (StringUtils.isNotEmpty(params.entry_no)) {
            criteria.andLike("entryNo", params.entry_no + "%");
        }
        if (params.type != null) {
            criteria.andEqualTo("type", params.type);
        }
        if (StringUtils.isNotEmpty(params.rfid)) {
            criteria.andLike("rfid", params.rfid + "%");
        }
        if (StringUtils.isNotEmpty(params.sku_no)) {
            criteria.andLike("skuNo", params.sku_no + "%");
        }
        if (StringUtils.isNotEmpty(params.bin_no)) {
            criteria.andLike("binNo", params.bin_no + "%");
        }
        if (StringUtils.isNotEmpty(params.op_id)) {
            criteria.andLike("opId", params.op_id + "%");
        }
        if (params.from_entry_time != null) {
            criteria.andGreaterThanOrEqualTo("entryTime", params.from_entry_time);
        }
        if (params.to_entry_time != null) {
            criteria.andLessThan("entryTime", params.to_entry_time);
        }

        PageHelper.startPage(params.pageIndex + 1, params.pageSize, true);

        List<Inventory> list = inventoryMapper.selectByExample(cond);

        PageInfo<Inventory> pageInfo = new PageInfo<>(list);

        result.setTotal(pageInfo.getTotal());
        result.setPageCount(pageInfo.getPages());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setData(list);

        return result;
    }

    @Override
    public List<Inventory> inbound(InboundRequestModel params) {
        List<Inventory> result = Lists.newArrayList();

        List<InboundRequestModel.Item> list = params.getItems();
        for (InboundRequestModel.Item item : list) {
            if (StringUtils.isEmpty(item.getSku_no())
                    || StringUtils.isEmpty(item.getBin_no())
                    || StringUtils.isEmpty(item.getRfid())) {
                continue;
            }

            String rfids[] = item.getRfid().split("[,;]");

            for (String rfid : rfids) {
                Inventory row = new Inventory();
                row.setEntryNo(new DateTime().toString("yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(5));
                row.setType(params.getType());
                row.setRfid(rfid);
                row.setSkuNo(item.getSku_no());
                row.setBinNo(item.getBin_no());
                row.setOpId(1);
                row.setEntryTime(new Date());

                try {
                    inventoryMapper.insert(row);

                    result.add(row);
                } catch (Exception e) {
                }
            }
        }

        return result;
    }
}
