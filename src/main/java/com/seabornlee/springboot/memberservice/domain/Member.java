package com.seabornlee.springboot.memberservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobile;
    private char VIP;

    public Member() { }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(Long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVIP() {
        return this.VIP == 'y';
    }

    public void setVIP() {
        this.VIP = 'y';
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
