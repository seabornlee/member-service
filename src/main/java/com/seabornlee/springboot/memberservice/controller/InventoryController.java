package com.seabornlee.springboot.memberservice.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "5库存", description = "库存")
@RequestMapping("")
@RestController
public class InventoryController {
}
