package com.seabornlee.springboot.memberservice.util.spider;

import org.junit.Test;

import static org.junit.Assert.*;

public class ESpiritSpiderTest {

    @Test
    public void getData() {

        ESpiritSpider spider = new ESpiritSpider("potato","potato-plan","http://www.runscm.com/user_login");

        System.out.println(spider.getData("https://hz1.ejlerp.com/warehouse/list",null,false,true).toString());
    }
}