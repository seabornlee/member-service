package com.seabornlee.springboot.memberservice.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by sh on 2018/8/3.
 */
@ApiModel(value = "库位绑定请求")
@Data
public class WarehouseBinBindRfidRequestModel {

    @ApiModelProperty(value = "rfid", example = "201800000001", required = true)
    private String rfid;

}
