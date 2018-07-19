package com.seabornlee.springboot.memberservice.mapper;

import com.github.pagehelper.Page;
import com.seabornlee.springboot.memberservice.domain.Member;

import java.util.List;

public interface MemberMapper {
    public Page<Member> getAll();
}
