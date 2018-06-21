package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member findBy(Long id) {
        return memberRepository.findById(id).get();
    }

    public boolean upgradeVIP(long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (!memberOptional.isPresent()) {
            return false;
        }

        Member member = memberOptional.get();
        member.setVIP();
        memberRepository.save(member);

        return true;
    }
}
