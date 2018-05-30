package org.rest.controller;

import org.rest.exception.UserDTOException;
import org.rest.model.UserDTO;
import org.rest.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/22.
 */
@Controller
@RequestMapping("index/")
public class IndexController {

    private UserService userService;

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
            value = "userDTOList"
            , method = RequestMethod.POST
            , consumes = "application/json")
    @ResponseBody
    public List<UserDTO> userDTOList(@RequestBody List<UserDTO> userDTOList) {
        return userDTOList;
    }


    /**
     * 作为@ResponseBody的取决方案，控制器可以访问ResponseEntity对象，包含响应相关的元数据（头部信息和状态码）
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        UserDTO userByName = userService.getUserByName(name);
        HttpStatus httpStatus = userByName == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(userByName, httpStatus);
    }


    @RequestMapping(value = "user/{name}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getUserDtoByName(@PathVariable String name) {
        UserDTO userByName = userService.getUserByName(name);
        if (userByName == null) {
            throw new UserDTOException(name + "用户不存在");
        }
        return userByName;
    }


    /**
     * 异常处理类，捕获UserDTOException，并进行处理
     *
     * @param userDTOException
     * @return
     */
    @ExceptionHandler(UserDTOException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserDTOException handleUserDTOException(UserDTOException userDTOException) {
        return userDTOException;
    }


    /**
     * 给返回值设置头信息
     *
     * @param userDTO
     * @param ucb
     * @return
     */
    @RequestMapping(
            value = "listUserDTO"
            , method = RequestMethod.POST
            , consumes = "application/json")
    public ResponseEntity<UserDTO> saveUserDto(@RequestBody UserDTO userDTO, UriComponentsBuilder ucb) {
        UserDTO user = userService.saveUser(userDTO);
        HttpHeaders headers = new HttpHeaders();
        URI uri = ucb.path("/").path(user.getName()).build().toUri();
        headers.setLocation(uri);
        ResponseEntity<UserDTO> userDTOResponseEntity = new ResponseEntity<>(user, headers, HttpStatus.CREATED);
        return userDTOResponseEntity;
    }


}
