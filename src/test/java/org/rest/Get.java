package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Area;
import org.rest.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by XiuYin.Cui on 2018/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Get {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test01(){
        Area area = httpUtil.getForObject();
        System.out.println(area);
    }
}
