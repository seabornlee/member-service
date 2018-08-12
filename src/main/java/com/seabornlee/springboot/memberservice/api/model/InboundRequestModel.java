package com.seabornlee.springboot.memberservice.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by sh on 2018/8/3.
 */
@ApiModel(value = "入库请求")
@Data
public class InboundRequestModel {

    @ApiModelProperty(value = "入库类型", example = "1", required = true)
    private Integer type;
    @ApiModelProperty(value = "商品数", example = "10", required = true)
    private Integer goods;
    @ApiModelProperty(value = "SKU种类", example = "3", required = true)
    private Integer sku;
    @ApiModelProperty(value = "入库明细", required = true)
    private List<Item> items;

    @Data
    public static class Item {
        @ApiModelProperty(value = "SKU编码", required = true)
        private String sku_no;
        @ApiModelProperty(value = "库位编码", required = true)
        private String bin_no;
        @ApiModelProperty(value = "RFID列表，多个RFID逗号分隔", required = true)
        private String rfid;
    }

}
