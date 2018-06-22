package com.seabornlee.springboot.memberservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "smsService")
public interface SMSClient {
    @RequestMapping(value = "/{mobile}/{templateId}", method = RequestMethod.POST, consumes = "application/json")
    boolean sendTo(@PathVariable("mobile") String mobile, @PathVariable("templateId") Long templateId);
}
