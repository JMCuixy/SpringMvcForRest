package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Area;
import org.rest.model.District;
import org.rest.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ExchangeRequestTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test01(){
        Area exchange = httpUtil.exchange();
        List<District> districts = exchange.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getAdcode());
    }
}
