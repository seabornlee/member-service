package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @Test
    public void should_get_member_by_id() {
        Mockito.when(memberService.findBy(1L)).thenReturn(new Member(1L, "Seaborn Lee"));

        given().
                standaloneSetup(memberController).
        when().
                get("/members/1").
        then().
                statusCode(200).
                body("id", equalTo(1)).
                body("name", equalTo("Seaborn Lee"));
    }

    @Test
    public void should_get_404_when_member_not_exist() {
        Mockito.when(memberService.findBy(1L)).thenThrow(new NoSuchElementException());

        given().
                standaloneSetup(memberController).
        when().
                get("/members/1").
        then().
                statusCode(404);
    }

    @Test
    public void should_get_all_members(){
        Mockito.when(memberService.selectAll()).thenThrow(new NoSuchElementException());

        given().
                standaloneSetup(memberController).
                when().
                get("/members/list").
                then().
                statusCode(404);
    }

}
