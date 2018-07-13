package com.seabornlee.springboot.memberservice.service;

import com.seabornlee.springboot.memberservice.client.SMSClient;
import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.seabornlee.springboot.memberservice.service.MemberService.SMS_TEMPLATE_ID_VIP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private SMSClient smsClient;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_upgrade_VIP_succeed() {
        // given
        long id = 1L;
        when(memberRepository.findById(id)).thenReturn(Optional.of(new Member(id, "Seaborn Lee")));

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
        String mobile = "17345041219";
        when(memberRepository.findById(id)).thenReturn(Optional.of(new Member(id, "Seaborn Lee", mobile)));

        // when
        boolean isSuccessfully = memberService.upgradeVIP(id);

        // then
        assertThat(isSuccessfully).isTrue();
        verify(smsClient, times(1)).sendTo(mobile, SMS_TEMPLATE_ID_VIP);
    }

    @Test
    public void should_not_upgrade_VIP_when_member_not_exist() {
        // when
        long not_exist_member_id = 99999L;
        when(memberRepository.findById(not_exist_member_id)).thenReturn(Optional.empty());
        boolean isSuccessfully = memberService.upgradeVIP(not_exist_member_id);

        // then
        assertThat(isSuccessfully).isFalse();
    }
}