package com.seabornlee.springboot.memberservice.controller;

import com.seabornlee.springboot.memberservice.domain.Member;
import com.seabornlee.springboot.memberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping(value = "/{id}")
    public @ResponseBody Member getById(@PathVariable Long id) {
        try {
            return memberService.findBy(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
