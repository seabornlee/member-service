package com.seabornlee.springboot.memberservice.controller;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.service.ISKUService;
import com.seabornlee.springboot.memberservice.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "1SKU", description = "SKU管理")
@RequestMapping("")
@RestController
public class SKUController {

    @Autowired
    private ISKUService skuService;

    @ApiOperation(value = "sku列表", notes = "分页获取sku列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataType = "query"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, defaultValue = "10", dataType = "query")
    })

    @GetMapping(value = "/skus", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSKUList(@RequestParam(required = false) String q,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        page = Constants.ensurePositiveValue(page, 1);
        size = Constants.ensurePositiveValue(size, Constants.DEFAULT_PAGE_SIZE);
        PageInfo<SKU> pageInfo = null;
        if (StringUtils.isNotBlank(q)) {
            pageInfo = skuService.searchListByKeyword(q, page, size);
        } else {
            pageInfo = skuService.getListByPage(new SKU(), page, size);
        }
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "sku绑定rfid列表", notes = "分页获取sku所绑定的rfid列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "skuId", value = "SKU id", required = true, dataType = "path"),
            @ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataType = "query"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, defaultValue = "10", dataType = "query")
    })
    @GetMapping(value = "/{skuId}/rfids", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSKUDetail(@PathVariable Integer skuId,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        page = Constants.ensurePositiveValue(page, 1);
        size = Constants.ensurePositiveValue(size, Constants.DEFAULT_PAGE_SIZE);
        PageInfo<SKU> pageInfo = null;
        /*if (StringUtils.isNotBlank(q)) {
            pageInfo = skuService.searchListByKeyword(q, page, size);
        } else {
            pageInfo = skuService.getListByPage(new SKU(), page, size);
        }*/
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }
}
