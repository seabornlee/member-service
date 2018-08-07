package com.seabornlee.springboot.memberservice.domain;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String warehouseNo;

    private Integer capacity;

    private Integer tenantId;

    public Integer getId() {
        return id;
    }

    public WarehouseBin setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getBinNo() {
        return binNo;
    }

    public WarehouseBin setBinNo(String binNo) {
        this.binNo = binNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public WarehouseBin setName(String name) {
        this.name = name;
        return this;
    }

    public String getAreaType() {
        return areaType;
    }

    public WarehouseBin setAreaType(String areaType) {
        this.areaType = areaType;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public WarehouseBin setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public WarehouseBin setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
        return this;
    }

    public Boolean getTmp() {
        return isTmp;
    }

    public WarehouseBin setTmp(Boolean tmp) {
        isTmp = tmp;
        return this;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public WarehouseBin setEnabled(Boolean enabled) {
        isEnabled = enabled;
        return this;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public WarehouseBin setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public WarehouseBin setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public WarehouseBin setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public WarehouseBin setAreaNo(String areaNo) {
        this.areaNo = areaNo;
        return this;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public WarehouseBin setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
        return this;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public WarehouseBin setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
