package com.seabornlee.springboot.memberservice.controller;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.service.IDataSyncRecordService;
import com.seabornlee.springboot.memberservice.util.Constants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data-sync-record")
public class DataSyncRecordController {

    private static final int DEFAULT_PAGE_SIZE = Constants.DEFAULT_PAGE_SIZE;

    @Autowired
    private IDataSyncRecordService dataSyncRecordService;

    @ApiOperation(value = "数据同步记录列表", notes = "分页获取数据同步记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, paramType = "query")
    })
    @GetMapping(value = "/records", produces = "application/json")
    @ResponseBody
    public ResponseEntity listRecord(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        page = Constants.ensurePositiveValue(page, 1);
        size = Constants.ensurePositiveValue(size, DEFAULT_PAGE_SIZE);
        PageInfo<DataSyncRecord> data = dataSyncRecordService.getListByPage(page, size);
        return new ResponseEntity(data, HttpStatus.OK);
    }

}
