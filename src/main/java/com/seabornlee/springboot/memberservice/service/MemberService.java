package com.seabornlee.springboot.memberservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.client.SMSClient;
import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.mapper.MemberMapper;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    public static final long SMS_TEMPLATE_ID_VIP = 3L;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SMSClient smsClient;

    @Autowired
    private MemberMapper memberMapper;

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

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAll(){
        return memberMapper.getAll();
    }

    public PageInfo<Member> getByPage(int page, int size){

        page = page>0?page:1;
        size = size>0?size:10;
        PageHelper.startPage(page, size,true);

        PageInfo<Member> pageInfo = new PageInfo<>(memberMapper.getAll());

        return pageInfo;
    }
}
