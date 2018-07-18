package com.seabornlee.springboot.memberservice.mapper;

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
        //assertTrue(null==list);
    }
}