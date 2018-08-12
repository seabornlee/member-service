package com.seabornlee.springboot.memberservice.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "warehouse_bin")
public class WarehouseBin {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "bin_no")
    private String binNo;

    private String name;

    @Column(name = "area_type")
    private String areaType;

    @Column(name = "area_name")
    private String areaName;

    private String areaNo;

    @Column(name = "shelf_no")
    private String shelfNo;

    @Column(name = "is_tmp")
    private Boolean isTmp;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "data_source")
    private Integer dataSource;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "warehouse_no")
    private String warehouseNo;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "tenant_id")
    private Integer tenantId;

    @Column(name = "rfid")
    private String rfid; // RFID 编号

}
