package com.seabornlee.springboot.memberservice.domain;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sku")
public class SKU {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "sku_no")
    private String skuNo;

    @Column(name = "bar_code")
    private String barCode;

    private String pic;

    @Column(name = "tenant_id")
    private Integer tenantId;

    @Column(name = "data_source")
    private Integer dataSource;

    public Integer getId() {
        return id;
    }

    public SKU setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public SKU setSkuNo(String skuNo) {
        this.skuNo = skuNo;
        return this;
    }

    public String getBarCode() {
        return barCode;
    }

    public SKU setBarCode(String barCode) {
        this.barCode = barCode;
        return this;
    }

    public String getPic() {
        return pic;
    }

    public SKU setPic(String pic) {
        this.pic = pic;
        return this;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public SKU setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public SKU setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
        return this;
    }
}
