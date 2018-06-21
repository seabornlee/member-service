package com.seabornlee.springboot.memberservice.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {

    @Test
    public void should_set_VIP() {
        // given
        Member member = new Member();

        // when
        member.setVIP();

        // then
        assertThat(member.isVIP()).isTrue();
    }
}