package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.client.SMSClient;
import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    public static final long SMS_TEMPLATE_ID_VIP = 3L;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SMSClient smsClient;

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

        smsClient.sendTo(member.getMobile(), SMS_TEMPLATE_ID_VIP);

        return true;
    }
}
