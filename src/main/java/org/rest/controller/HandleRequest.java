package org.rest.controller;

import org.rest.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
