package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.UserDTO;
import org.rest.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XiuYin.Cui on 2018/5/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PutRequestTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void test01() {
        httpUtil.put("张三");
    }

    @Test
    public void test02() {
        UserDTO userDTO = new UserDTO("admin", "nimda", 25, true);
        httpUtil.put(userDTO);
    }

    @Test
    public void test03() {
        UserDTO userDTO = new UserDTO("admin", "nimda", 25, true);
        Map<String, Object> map = new HashMap<>(16);
        map.put("name", userDTO.getName());
        map.put("password", userDTO.getPassword());
        map.put("age", 25);
        map.put("valid", true);
        httpUtil.put(map);
    }
}
