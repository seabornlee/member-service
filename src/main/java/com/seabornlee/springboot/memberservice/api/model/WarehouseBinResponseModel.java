package com.seabornlee.springboot.memberservice.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.cloud.common.api.ApiBaseResponseModel;
import com.seabornlee.springboot.memberservice.domain.WarehouseBin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by sh on 2018/8/10.
 */
@ApiModel(value = "库位信息")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseBinResponseModel extends ApiBaseResponseModel {

    @ApiModelProperty(value = "库位id", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "库位编号", required = true, example = "K01-13-05")
    private String bin_no;
    @ApiModelProperty(value = "库位绑定的RFID", required = true, example = "2018081000000001")
    private String rfid;

    public WarehouseBinResponseModel from(WarehouseBin from) {
        this.id = from.getId();
        this.bin_no = from.getBinNo();
        this.rfid = from.getRfid();

        return this;
    }
}
