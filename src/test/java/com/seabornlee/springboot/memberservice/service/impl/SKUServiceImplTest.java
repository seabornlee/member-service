package com.seabornlee.springboot.memberservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.seabornlee.springboot.memberservice.domain.SKU;
import com.seabornlee.springboot.memberservice.mapper.SKUMapper;
import com.seabornlee.springboot.memberservice.service.ISKUService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class SKUServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private SKUMapper skuMapper;

    @InjectMocks
    private SKUServiceImpl skuService;

    @Test
    public void getListByPage() {

        PageInfo<SKU> pageInfo = skuService.getListByPage(new SKU(),1,10);

        assertThat(pageInfo.getList()).isNull();
    }

    @Test
    public void existSKU() {

        assertThat(skuService.existSKU(new SKU())).isFalse();
    }

    @Test
    public void saveSKU() {

        SKUServiceImpl mock = spy(skuService);
        mock.saveSKU(new SKU());
        verify(spy(mock), times(1));
    }

    @Test
    public void saveOrUpdate() {
        SKUServiceImpl mock = spy(skuService);
        mock.saveOrUpdate(new SKU());
        verify(spy(mock), times(1));
    }

    @Test
    public void updateSKU() {
        SKUServiceImpl mock = spy(skuService);
        mock.updateSKU(new SKU());
        verify(spy(mock), times(1));
    }

    @Test
    public void deleteSKU() {
        SKUServiceImpl mock = spy(skuService);
        mock.deleteSKU(new SKU());
        verify(spy(mock), times(1));
    }
}