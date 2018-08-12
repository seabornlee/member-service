package com.seabornlee.springboot.memberservice.controller;

import com.google.common.collect.Lists;
import com.potato.cloud.common.CallResult;
import com.potato.cloud.common.api.ApiListResponseModel;
import com.potato.cloud.common.dao.PageResult;
import com.potato.cloud.common.util.WebUtils;
import com.seabornlee.springboot.memberservice.api.model.WarehouseBinBindRfidRequestModel;
import com.seabornlee.springboot.memberservice.api.model.WarehouseBinResponseModel;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.domain.querycondition.WarehouseBinQueryCondition;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import com.seabornlee.springboot.memberservice.util.Constants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(tags = "2库位", description = "库位相关操作")
@RequestMapping("")
@RestController
public class WarehouseBinController {

    @Autowired
    private IWarehouseBinService warehouseBinService;

    @ApiOperation(value = "库位搜索", notes = "")
    @GetMapping(value = "/warehouse-bins")
    public ApiListResponseModel<WarehouseBinResponseModel> warehouse_bins(
            @ApiParam("库位编号") @RequestParam(required = false) String bin_no,
            @ApiParam("是否已绑定RFID：0 - 未绑定；1 - 已绑定") @RequestParam(required = false) Integer bind_rfid,
            @ApiParam("页数,从1开始") @RequestParam(required = false) Integer page_index,
            @ApiParam("页大小") @RequestParam(required = false) Integer page_size,
            HttpServletRequest request, HttpServletResponse response) {
        ApiListResponseModel<WarehouseBinResponseModel> result = new ApiListResponseModel<WarehouseBinResponseModel>();

        WarehouseBinQueryCondition params = new WarehouseBinQueryCondition(WebUtils.getParameterMap(request));
        params.setTenant_id(551575);

        PageResult<WarehouseBin> pageResult = warehouseBinService.query(params);

        List<WarehouseBinResponseModel> list = Lists.newArrayList();
        for (WarehouseBin row : pageResult.getData()) {
            list.add(new WarehouseBinResponseModel().from(row));
        }
        result.setTotal(pageResult.getTotal());
        result.setPage_index(pageResult.getPageIndex());
        result.setPage_size(pageResult.getPageSize());
        result.setResults(list);

        return result.response(response);
    }

    @ApiOperation(value = "库位绑定", notes = "")
    @PostMapping(value = "/warehouse-bins/{bin_no}/rfid")
    public WarehouseBinResponseModel warehouse_bins_bind_rfid(
            @ApiParam("库位编号") @PathVariable("bin_no") String bin_no,
            @RequestBody WarehouseBinBindRfidRequestModel params,
            HttpServletRequest request, HttpServletResponse response) {
        WarehouseBinResponseModel result = new WarehouseBinResponseModel();

        CallResult<WarehouseBin> callr = warehouseBinService.bindRfid(bin_no, params.getRfid());

        result.put(callr);
        if (callr.success()) {
            WarehouseBin warehouseBin = callr.getData();
            result.from(warehouseBin);
        }

        return result.response(response);
    }

    @ApiOperation(value = "库位列表", notes = "分页获取库位列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataType = "query"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, defaultValue = "10", dataType = "query")
    })
    @GetMapping(value = "/warehouseBin/list", produces = "application/json")
    @ResponseBody
    public ResponseEntity getWarehouseBinList(@RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer size) {
        page = Constants.ensurePositiveValue(page,1);
        size = Constants.ensurePositiveValue(size, Constants.DEFAULT_PAGE_SIZE);
        return new ResponseEntity(warehouseBinService.getListByPage(new WarehouseBin(), page, size), HttpStatus.OK);
    }
}
