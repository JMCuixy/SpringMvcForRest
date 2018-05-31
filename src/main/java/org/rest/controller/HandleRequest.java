package org.rest.controller;

import org.rest.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created by XiuYin.Cui on 2018/5/28.
 */
@Controller
@RequestMapping("rest")
public class HandleRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleRequest.class);

    /**
     * 更新资源
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public UserDTO handlePutRequest(@RequestParam(name = "user") String user) {
        LOGGER.info(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user);
        return userDTO;
    }


    /**
     * 更新对象
     */
    @RequestMapping(value = "/updateDto", method = RequestMethod.PUT)
    @ResponseBody
    public UserDTO handlePutRequest(@ModelAttribute UserDTO userDTO) {
        return userDTO;
    }


    /**
     * 删除资源
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String handleDeleteRequest(@RequestParam String uuid) {
        return uuid;
    }


    /**
     * 新增资源
     *
     * @param userDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> handlePostRequest(@RequestBody UserDTO userDTO, HttpServletRequest request, UriComponentsBuilder ucb) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri = ucb.path("/index/").path(userDTO.getName()).build().toUri();
        httpHeaders.setLocation(uri);
        ResponseEntity<UserDTO> userDTOResponseEntity = new ResponseEntity<>(userDTO , httpHeaders, HttpStatus.CREATED);
        return userDTOResponseEntity;
    }
}
