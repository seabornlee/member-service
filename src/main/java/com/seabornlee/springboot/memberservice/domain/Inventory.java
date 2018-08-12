package com.seabornlee.springboot.memberservice.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

/**
 * 库存
 *
 * Created by sh on 2018/8/11.
 */
@Data
@Table(name = "inventory")
public class Inventory {

    @Column(name = "id")
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id; // id标识
    @Column(name = "entry_no")
    private String entryNo; // 入库单号
    @Column(name = "type")
    private Integer type; // 入库类型
    @Column(name = "rfid")
    private String rfid; // 入库RFID
    @Column(name = "sku_no")
    private String skuNo; // 入库SKU编号
    @Column(name = "bin_no")
    private String binNo; // 库位编号
    @Column(name = "op_id")
    private Integer opId; // 经办人
    @Column(name = "entry_time")
    private Date entryTime; // 入库时间

    @Data
    public static class QueryCondition extends com.potato.cloud.common.dao.QueryCondition {

        public Integer tenant_id;
        public String entry_no;
        public Integer type;
        public String rfid;
        public String sku_no;
        public String bin_no;
        public String op_id;
        public Date from_entry_time;
        public Date to_entry_time;

        public QueryCondition(Map params) {
            super(params);
        }
    }
}
