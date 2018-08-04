package com.seabornlee.springboot.memberservice.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MemberMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private MemberMapper memberMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAll() {
        List<Member> list = memberMapper.getAll();
        logger.debug(list.toString());
        assertThat(list).isNotEmpty();
    }

    @Test
    public void test_get_by_page(){
        PageHelper.startPage(1, 10,true);

        PageInfo<Member> pageInfo = new PageInfo<>(memberMapper.getAll());

        logger.debug(pageInfo.toString());

        assertThat(pageInfo).isNotNull();
    }

    @Test
    public void test_common_mapper(){

        List<Member> list = memberMapper.getAll();

        logger.debug(list.toString());
        assertThat(list).isNotNull();
    }
}