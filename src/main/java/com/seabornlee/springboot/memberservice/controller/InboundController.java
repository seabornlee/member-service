package com.seabornlee.springboot.memberservice.controller;

import com.google.common.collect.Lists;
import com.potato.cloud.auc.common.bo.LoginUser;
import com.potato.cloud.auc.common.util.AucUtils;
import com.potato.cloud.common.api.ApiListResponseModel;
import com.potato.cloud.common.dao.PageResult;
import com.potato.cloud.common.util.WebUtils;
import com.seabornlee.springboot.memberservice.api.model.InboundRequestModel;
import com.seabornlee.springboot.memberservice.api.model.InventoryResponseModel;
import com.seabornlee.springboot.memberservice.domain.Inventory;
import com.seabornlee.springboot.memberservice.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "3入库", description = "入库管理")
@RequestMapping("")
@RestController
public class InboundController {

    @Autowired
    InventoryService inventoryService;

    @ApiOperation(value = "入库明细", notes = "")
    @GetMapping(value = "/inbounds")
    public ApiListResponseModel<InventoryResponseModel> inbounds(
            @ApiParam("入库单号") @RequestParam(required = false) String entry_no,
            @ApiParam("入库类型：1 - 采购入库；2 - 退货入库") @RequestParam(required = false) Integer type,
            @ApiParam("入库RFID") @RequestParam(required = false) String rfid,
            @ApiParam("入库SKU编号") @RequestParam(required = false) String sku_no,
            @ApiParam("库位编号") @RequestParam(required = false) String bin_no,
            @ApiParam("经办人") @RequestParam(required = false) String op_id,
            @ApiParam("入库开始时间") @RequestParam(required = false) String from_entry_time,
            @ApiParam("入库截止时间") @RequestParam(required = false) String to_entry_time,
            @ApiParam("页数,从1开始") @RequestParam(required = false) Integer page_index,
            @ApiParam("页大小") @RequestParam(required = false) Integer page_size,
            HttpServletRequest request, HttpServletResponse response) {
        ApiListResponseModel<InventoryResponseModel> result = new ApiListResponseModel<InventoryResponseModel>();

        Inventory.QueryCondition params = new Inventory.QueryCondition(WebUtils.getParameterMap(request));
        params.setTenant_id(551575);

        if (params.to_entry_time != null) {
            params.to_entry_time = new DateTime(params.to_entry_time).plusDays(1).toDate();
        }

        PageResult<Inventory> pageResult = inventoryService.query(params);

        List<InventoryResponseModel> list = Lists.newArrayList();
        for (Inventory row : pageResult.getData()) {
            list.add(new InventoryResponseModel().from(row));
        }
        result.setTotal(pageResult.getTotal());
        result.setPage_index(pageResult.getPageIndex());
        result.setPage_size(pageResult.getPageSize());
        result.setResults(list);

        return result.response(response);
    }

    @ApiOperation(value = "入库")
    @PostMapping("/inbounds")
    public ApiListResponseModel<InventoryResponseModel> inbounds_create(
            @RequestBody InboundRequestModel params,
            HttpServletRequest request, HttpServletResponse response) {
        ApiListResponseModel<InventoryResponseModel> result = new ApiListResponseModel<InventoryResponseModel>();

        LoginUser loginUser = AucUtils.getLoginUser();

        List<Inventory> list = inventoryService.inbound(params);

        List<InventoryResponseModel> results = Lists.newArrayList();
        for (Inventory row : list) {
            results.add(new InventoryResponseModel().from(row));
        }
        result.setTotal((long) results.size());
        result.setPage_index(1);
        result.setPage_size(0);
        result.setResults(results);

        return result.response(response);
    }
}
