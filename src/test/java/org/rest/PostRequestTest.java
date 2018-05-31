package org.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rest.model.Area;
import org.rest.model.District;
import org.rest.model.UserDTO;
import org.rest.net.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
        UserDTO userDTO = new UserDTO("admin", "nimda", 25, true);
        UserDTO userResult = httpUtil.postForObject(userDTO);
        System.out.println(userResult.getName());
    }

    @Test
    public void test02() {
        UserDTO userDTO = new UserDTO("admin", "nimda", 25, true);
        ResponseEntity<UserDTO> userDTOResponseEntity = httpUtil.postForEntity(userDTO);
        HttpStatus statusCode = userDTOResponseEntity.getStatusCode();
        System.out.println(statusCode);
    }
}
