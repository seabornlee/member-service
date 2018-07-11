package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping
    public @ResponseBody ResponseEntity<Member> create(@RequestBody Member member) {
        Member result = memberService.save(member);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Member getById(@PathVariable Long id) {
        try {
            return memberService.findBy(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
