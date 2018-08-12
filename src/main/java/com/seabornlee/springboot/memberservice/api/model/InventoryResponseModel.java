package com.seabornlee.springboot.memberservice.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato.cloud.common.api.ApiBaseResponseModel;
import com.seabornlee.springboot.memberservice.domain.Inventory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

/**
 * Created by sh on 2018/8/10.
 */
@ApiModel(value = "库存信息")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryResponseModel extends ApiBaseResponseModel {

    @ApiModelProperty(value = "库存id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "入库单号", example = "2018081000000001")
    private String entry_no;
    @ApiModelProperty(value = "入库类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "入库RFID", example = "RKD201807051002")
    private String rfid;
    @ApiModelProperty(value = "入库SKU编号", example = "XCD02461L")
    private String sku_no;
    @ApiModelProperty(value = "库位编号", example = "K01-01-05")
    private String bin_no;
    @ApiModelProperty(value = "经办人", example = "张三")
    private Integer op_id;
    @ApiModelProperty(value = "入库时间", example = "2018-10-10 10:10:10")
    private String entry_time;

    public InventoryResponseModel from(Inventory inventory) {
        this.id = inventory.getId();
        this.entry_no = inventory.getEntryNo();
        this.type = inventory.getType();
        this.rfid = inventory.getRfid();
        this.sku_no = inventory.getSkuNo();
        this.bin_no = inventory.getBinNo();
        this.op_id = inventory.getOpId();
        this.entry_time = new DateTime(inventory.getEntryTime()).toString("yyyy-MM-dd HH:mm:ss");

        return this;
    }
}
