package com.seabornlee.springboot.memberservice.mapper;

import com.github.pagehelper.Page;
import com.seabornlee.springboot.memberservice.domain.Member;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemberMapper extends Mapper<Member> {
    public Page<Member> getAll();
}
