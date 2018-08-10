package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.service.IDataSyncService;
import com.seabornlee.springboot.memberservice.util.enumeration.DataType;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/data-sync")
public class DataSyncController {

    @Autowired
    private IDataSyncService dataSyncService;

    @ApiOperation(value = "同步数据", notes = "同步e精灵数据到数据库")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "dataType", value = "数据类型", required = true)
    })
    @GetMapping(value = "/sync", produces = "application/json")
    @ResponseBody
    public ResponseEntity syncData(HttpServletRequest request,
                                   @RequestParam Integer dataType) {

        DataType type = DataType.valueOf(dataType);

        if (null == type) {
            return new ResponseEntity("dataType is not supported", HttpStatus.OK);
        }

        DataSyncRecord record = dataSyncService.sync(request, type);

        return new ResponseEntity(record, HttpStatus.OK);
    }
}
