package com.seabornlee.springboot.memberservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato.cloud.common.CallResult;
import com.potato.cloud.common.dao.PageResult;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.domain.querycondition.WarehouseBinQueryCondition;
import com.seabornlee.springboot.memberservice.mapper.WarehouseBinMapper;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class WarehouseBinServiceImpl implements IWarehouseBinService {

    @Autowired
    private WarehouseBinMapper warehouseBinMapper;

    @Override
    public PageResult<WarehouseBin> query(WarehouseBinQueryCondition params) {
        PageResult<WarehouseBin> result = new PageResult<WarehouseBin>();

        Condition cond = new Condition(WarehouseBin.class);
        Example.Criteria criteria = cond.createCriteria();

        criteria.andEqualTo("tenantId", params.tenant_id);
        if (StringUtils.isNotEmpty(params.bin_no)) {
            criteria.andLike("binNo", params.bin_no + "%");
        }
        if (params.bind_rfid != null) {
            if (params.bind_rfid == 0) {
                criteria.andIsNull("rfid");
            } else {
                criteria.andIsNotNull("rfid");
            }
        }

        PageHelper.startPage(params.pageIndex + 1, params.pageSize, true);

        List<WarehouseBin> list = warehouseBinMapper.selectByExample(cond);

        PageInfo<WarehouseBin> pageInfo = new PageInfo<>(list);

        result.setTotal(pageInfo.getTotal());
        result.setPageCount(pageInfo.getPages());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setData(list);

        return result;
    }

    @Override
    public CallResult<WarehouseBin> bindRfid(String binNo, String rfid) {
        CallResult<WarehouseBin> result = new CallResult<WarehouseBin>();

        WarehouseBin where = new WarehouseBin();
        where.setBinNo(binNo);

        List<WarehouseBin> list = warehouseBinMapper.select(where);
        if (list == null || list.size() == 0) {
            result.setCode(4);
            result.setMessage("库位编号不存在:" + binNo);
        } else if (list.size() > 1) {
            result.setCode(4);
            result.setMessage("库位编号不唯一:" + binNo);
        } else {
            WarehouseBin warehouseBin = list.get(0);
            if (StringUtils.isNotEmpty(warehouseBin.getRfid())) {
                result.setCode(4);
                result.setMessage("该库位已经绑定RFID:" + warehouseBin.getRfid());
            } else {
                warehouseBin.setRfid(rfid);
                if (warehouseBinMapper.updateByPrimaryKey(warehouseBin) > 0) {
                    result.setCode(0);
                    result.setMessage("库位绑定成功");
                    result.setData(warehouseBin);
                } else {
                    result.setCode(4);
                    result.setMessage("库位绑定失败");
                }
            }
        }

        return result;
    }

    @Override
    public PageInfo<WarehouseBin> getListByPage(WarehouseBin query, int page, int size) {
        page = page > 0 ? page : 1;
        size = size > 0 ? size : 10;
        PageHelper.startPage(page, size, true);
        List<WarehouseBin> list = null == query ? warehouseBinMapper.selectAll() : warehouseBinMapper.select(query);
        PageInfo<WarehouseBin> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Boolean existWarehouseBin(WarehouseBin warehouseBin) {
        WarehouseBin binFromDb = warehouseBinMapper.selectOne(warehouseBin);
        return null != binFromDb;
    }

    @Override
    public void saveWarehouseBin(WarehouseBin warehouseBin) {
        warehouseBinMapper.insert(warehouseBin);
    }

    @Override
    public void saveOrUpdate(WarehouseBin warehouseBin) {
        WarehouseBin query = new WarehouseBin();
        query.setBinNo(warehouseBin.getBinNo());
        query.setDataSource(warehouseBin.getDataSource());
        query.setTenantId(warehouseBin.getTenantId());
        if (existWarehouseBin(query)) {
            WarehouseBin binFromDb = warehouseBinMapper.selectOne(query);
            warehouseBin.setId(binFromDb.getId());
            if (null != warehouseBin.getId()) {
                updateWarehouseBin(warehouseBin);
            }
        } else {
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
