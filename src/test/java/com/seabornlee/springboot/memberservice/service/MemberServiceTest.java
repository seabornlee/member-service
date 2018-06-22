package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.client.SMSClient;
import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.seabornlee.springboot.memberservice.service.MemberService.SMS_TEMPLATE_ID_VIP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @MockBean
    private SMSClient smsClient;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void should_upgrade_VIP_succeed() {
        // given
        long id = 1L;
        memberRepository.save(new Member(id, "Seaborn Lee"));

        // when
        boolean isSuccessfully = memberService.upgradeVIP(id);

        // then
        assertThat(isSuccessfully).isTrue();
        Member member = memberService.findBy(id);
        assertThat(member.isVIP()).isTrue();
    }

    @Test
    public void should_send_SMS_when_upgrade_VIP_succeed() {
        // given
        long id = 1L;
        memberRepository.save(new Member(id, "Seaborn Lee", "17345041219"));

        // when
        boolean isSuccessfully = memberService.upgradeVIP(id);

        // then
        assertThat(isSuccessfully).isTrue();
        verify(smsClient, times(1)).sendTo("17345041219", SMS_TEMPLATE_ID_VIP);
    }

    @Test
    public void should_not_upgrade_VIP_when_member_not_exist() {
        // when
        long not_exist_member_id = 99999L;
        boolean isSuccessfully = memberService.upgradeVIP(not_exist_member_id);

        // then
        assertThat(isSuccessfully).isFalse();
    }
}