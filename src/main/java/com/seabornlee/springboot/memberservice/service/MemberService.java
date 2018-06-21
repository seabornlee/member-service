package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public Member findBy(Long id) {
        return new Member(1L, "Ssssssssssssss");
    }
}
