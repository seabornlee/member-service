package com.seabornlee.springboot.memberservice.controller;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.DataSyncRecord;
import com.seabornlee.springboot.memberservice.service.IDataSyncRecordService;
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
@RequestMapping("/dataSyncRecord")
public class DataSyncRecordController {

    private final static int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private IDataSyncRecordService dataSyncRecordService;

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public ResponseEntity listRecord(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size){
        page = null==page||page<=0?1:page;
        size = null==size||size<=0?DEFAULT_PAGE_SIZE:size;
        PageInfo<DataSyncRecord> data = dataSyncRecordService.getListByPage(page,size);
        return new ResponseEntity(data,HttpStatus.OK);
    }

}
