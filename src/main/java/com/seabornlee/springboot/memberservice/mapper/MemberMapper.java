package com.seabornlee.springboot.memberservice.mapper;

import com.seabornlee.springboot.memberservice.domain.Member;

import java.util.List;

public interface MemberMapper {
    public List<Member> getAll();
}
