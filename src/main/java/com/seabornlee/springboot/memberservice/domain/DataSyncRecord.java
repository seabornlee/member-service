package com.seabornlee.springboot.memberservice.domain;

import com.seabornlee.springboot.memberservice.util.enumeration.DataSourceEnum;
import com.seabornlee.springboot.memberservice.util.enumeration.DataSyncStatus;
import io.swagger.models.auth.In;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DataSyncRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_source")
    private DataSourceEnum dataSource;

    @Column(name = "url")
    private String url;

    @Column(name = "method")
    private String method;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "operator")
    private String operator;

    @Column(name = "op_time")
    private Long opTime;

    @Column(name = "is_success")
    private Boolean isSuccess;

    private DataSyncStatus status;//任务执行状态

    public Integer getId() {
        return id;
    }

    public DataSyncRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDataSource() {
        return dataSource.getValue();
    }

    public DataSyncRecord setDataSource(int dataSource) {
        this.dataSource = DataSourceEnum.valueOf(dataSource);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSyncRecord setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public DataSyncRecord setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getParameters() {
        return parameters;
    }

    public DataSyncRecord setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public DataSyncRecord setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public Long getOpTime() {
        return opTime;
    }

    public DataSyncRecord setOpTime(Long opTime) {
        this.opTime = opTime;
        return this;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public DataSyncRecord setSuccess(Boolean success) {
        isSuccess = success;
        return this;
    }

    public Integer getStatus() {
        return status.getValue();
    }

    public DataSyncRecord setStatus(Integer status) {
        this.status = DataSyncStatus.valueOf(status);
        return this;
    }
}
