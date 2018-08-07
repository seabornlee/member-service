package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import com.seabornlee.springboot.memberservice.service.IWarehouseBinService;
import com.seabornlee.springboot.memberservice.util.Constants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/warehouse-bin")
public class WarehouseBinController {

    @Autowired
    private IWarehouseBinService warehouseBinService;

    @ApiOperation(value = "库位列表", notes = "分页获取库位列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataType = "query"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, defaultValue = "10", dataType = "query")
    })
    @GetMapping(value = "/bins", produces = "application/json")
    @ResponseBody
    public ResponseEntity getWarehouseBinList(@RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer size) {
        page = Constants.ensurePositiveValue(page, 1);
        size = Constants.ensurePositiveValue(size, Constants.DEFAULT_PAGE_SIZE);
        return new ResponseEntity(warehouseBinService.getListByPage(new WarehouseBin(), page, size), HttpStatus.OK);
    }
}
