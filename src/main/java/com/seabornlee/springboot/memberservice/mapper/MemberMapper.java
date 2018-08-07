package com.seabornlee.springboot.memberservice.mapper;

import com.github.pagehelper.Page;
import com.seabornlee.springboot.memberservice.domain.Member;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {
    public Page<Member> getAll();
}
