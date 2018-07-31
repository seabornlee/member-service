package com.seabornlee.springboot.memberservice.util.spider;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ESpiritSpiderTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getData() {

        ESpiritSpider spider = new ESpiritSpider("potato","potato-plan","http://www.runscm.com/user_login");

        Object data = spider.getData("https://hz1.ejlerp.com/warehouse/list",null,false,true);

        logger.info(data.toString());

        assertTrue(null!=data);
    }
}