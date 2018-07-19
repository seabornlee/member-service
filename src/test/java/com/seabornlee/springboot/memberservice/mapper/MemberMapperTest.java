package com.seabornlee.springboot.memberservice.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MemberMapperTest {

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
        System.out.println(list);
    }

    @Test
    public void test_get_by_page(){
        PageHelper.startPage(1, 10,true);

        PageInfo<Member> pageInfo = new PageInfo<>(memberMapper.getAll());

        System.out.println(pageInfo);
    }
}