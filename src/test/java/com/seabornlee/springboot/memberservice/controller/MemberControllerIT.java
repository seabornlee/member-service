package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.BaseIntegrationTest;
import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class MemberControllerIT extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    public void should_create_member() {
        Member member = new Member();
        member.setName("Seaborn Lee");
        member.setMobile("17345041219");

        given()
            .body(member)
            .contentType(ContentType.JSON).
        when()
            .post("/members").
        then()
            .statusCode(201);
    }

    @Test
    public void should_find_member_when_member_exists() {
        // given
        memberRepository.save(new Member(1L, "Seaborn Lee", "17345041219"));

        when().
            get("/members/1").
        then().
            statusCode(200).
            body("id", equalTo(1)).
            body("name", equalTo("Seaborn Lee")).
            body("mobile", equalTo("17345041219"));
    }

    @Test
    public void should_return_404_when_member_not_exist() {
        // given
        memberRepository.deleteAll();

        when().
                get("/members/1").
        then().
                statusCode(404);
    }
}
