package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Area;
import org.rest.model.District;
import org.rest.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiuYin.Cui on 2018/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PostRequestTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test01() {
        Area area = httpUtil.postForObject();
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getName());
        System.out.println(district.getCitycode());

    }

    @Test
    public void test02() {
        Area area = httpUtil.postForObject("2c95fdacd3f72bdbfec55bd7eac7b5c0");
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getName());
        System.out.println(district.getCitycode());
    }

    @Test
    public void test03() {
        Map<String, String> map = new HashMap<>(4);
        map.put("key", "2c95fdacd3f72bdbfec55bd7eac7b5c0");
        Area area = httpUtil.postForObject(map);
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getName());
        System.out.println(district.getCitycode());
    }

    @Test
    public void test04() {
        HttpEntity<Area> area = httpUtil.postForEntity(Area.class);
        HttpHeaders headers = area.getHeaders();
        System.out.println(headers.getContentLength());
    }
}
