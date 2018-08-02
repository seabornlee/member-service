package com.seabornlee.springboot.memberservice.domain;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class Warehouse {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "warehouse_no")
    private String warehouseNo;

    private String name;

    private String type;

    @Column(name = "is_master")
    private Boolean isMaster;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "is_position_enabled")
    private Boolean isPositionEnabled;

    private Integer province;

    private Integer city;

    private Integer district;

    private String address;

    @Column(name = "tenant_id")
    private Integer tenantId;

    @Column(name = "data_source")
    private Integer dataSource;

    public Integer getId() {
        return id;
    }

    public Warehouse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public Warehouse setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Warehouse setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Warehouse setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getMaster() {
        return isMaster;
    }

    public Warehouse setMaster(Boolean master) {
        isMaster = master;
        return this;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public Warehouse setDefault(Boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    public Boolean getPositionEnabled() {
        return isPositionEnabled;
    }

    public Warehouse setPositionEnabled(Boolean positionEnabled) {
        isPositionEnabled = positionEnabled;
        return this;
    }

    public Integer getProvince() {
        return province;
    }

    public Warehouse setProvince(Integer province) {
        this.province = province;
        return this;
    }

    public Integer getCity() {
        return city;
    }

    public Warehouse setCity(Integer city) {
        this.city = city;
        return this;
    }

    public Integer getDistrict() {
        return district;
    }

    public Warehouse setDistrict(Integer district) {
        this.district = district;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Warehouse setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public Warehouse setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public Warehouse setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
        return this;
    }
}
