package org.rest.controller;

import org.rest.model.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/22.
 */
@Controller
public class IndexController {

    @RequestMapping("index.html")
    public String index() {
        return "index";
    }

    /**
     * 编写REST端点，@ResponseBody 将返回的对象作为资源发送给客户端，并将其转换为客户端可接受的表述形式
     *
     * @return
     */
    @RequestMapping("listUserDTO")
    @ResponseBody
    public List<UserDTO> listUserDTO() {
        List<UserDTO> list = new ArrayList<>();
        UserDTO userDTO1 = new UserDTO("张三", "admin", 18, true);
        UserDTO userDTO2 = new UserDTO("李四", "admin", 19, false);
        UserDTO userDTO3 = new UserDTO("王五", "admin", 20, true);
        list.add(userDTO1);
        list.add(userDTO2);
        list.add(userDTO3);
        return list;
    }

    /**
     * 编写REST端点，@RequestBody 接收客户端的资源对象，并将客户端的资源表述转换为对象。
     * (DispatcherServlet 会承担这份工作，找到合适的消息转换器进行 JSON 和 对象之间的转换)
     *
     * @return
     */
    @RequestMapping(
            value = "listUserDTO"
            , method = RequestMethod.POST
            , consumes = "application/json")
    @ResponseBody
    public List<UserDTO> userDTOList(@RequestBody List<UserDTO> userDTOList) {
        return userDTOList;
    }

}
