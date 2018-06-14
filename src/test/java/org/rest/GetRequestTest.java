package org.rest;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Area;
import org.rest.model.District;
import org.rest.net.HttpUtil;
import org.rest.tool.PinYinUtil;
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
public class GetRequestTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test01() throws BadHanyuPinyinOutputFormatCombination {
        Area area = httpUtil.getForObject();
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        List<District> districtList = district.getDistricts();
        for (District d : districtList) {
            String father = d.getAdcode();
            List<District> list = d.getDistricts();
            for (District di : list) {
                String adCode = di.getAdcode();
                String name = di.getName();
                String center = di.getCenter();
                String sql = "INSERT INTO yy_system_city(uuid,cityID,city,father,center,short_pinyin,full_pinyin,city_no) SELECT REPLACE(UUID(),'-',''),'" + adCode + "','" + name + "','" + center + "','" + father + "','" + PinYinUtil.chineseToPinYinSToLowerCase(name) + "','" + PinYinUtil.chineseToPinYin(name) + "','" + PinYinUtil.chineseToPinYinSToUpperCase(name) + "' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM yy_system_city WHERE cityID = '"+adCode+"');";
                System.out.println(sql);
                String father2 = di.getAdcode();
                List<District> list1 = di.getDistricts();
                for (District dis: list1){
                    adCode = dis.getAdcode();
                    name = dis.getName();
                    center = dis.getCenter();
                    String sql_two = "INSERT INTO yy_system_city(uuid,cityID,city,father,center,short_pinyin,full_pinyin,city_no) SELECT REPLACE(UUID(),'-',''),'" + adCode + "','" + name + "','" + center + "','" + father2 + "','" + PinYinUtil.chineseToPinYinSToLowerCase(name) + "','" + PinYinUtil.chineseToPinYin(name) + "','" + PinYinUtil.chineseToPinYinSToUpperCase(name) + "' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM yy_system_city WHERE cityID = '"+adCode+"');";
                    System.out.println(sql_two);
                }

            }
        }

    }

    @Test
    public void test02() {
        Area area = httpUtil.getForObject("2c95fdacd3f72bdbfec55bd7eac7b5c0");
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getName());
        System.out.println(district.getCitycode());
    }

    @Test
    public void test03() {
        Map<String, String> map = new HashMap<>(4);
        map.put("key", "2c95fdacd3f72bdbfec55bd7eac7b5c0");
        Area area = httpUtil.getForObject(map);
        List<District> districts = area.getDistricts();
        District district = districts.get(0);
        System.out.println(district.getName());
        System.out.println(district.getCitycode());
    }

    @Test
    public void test04() {
        HttpEntity<Area> area = httpUtil.getForEntity(Area.class);
        HttpHeaders headers = area.getHeaders();
        System.out.println(headers.getContentLength());
    }
}
