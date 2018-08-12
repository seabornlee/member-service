package com.seabornlee.springboot.memberservice.service;

import com.potato.cloud.common.dao.PageResult;
import com.seabornlee.springboot.memberservice.api.model.InboundRequestModel;
import com.seabornlee.springboot.memberservice.domain.Inventory;

import java.util.List;

/**
 * 库存接口
 *
 * Created by sh on 2018/8/11.
 */
public interface InventoryService {

    /**
     * 查询库存明细
     *
     * @param params
     * @return
     */
    PageResult<Inventory> query(Inventory.QueryCondition params);

    /**
     * 入库
     * @param params
     * @return
     */
    List<Inventory> inbound(InboundRequestModel params);

}
