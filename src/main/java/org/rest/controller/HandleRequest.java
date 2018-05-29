package org.rest.controller;

import org.rest.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by XiuYin.Cui on 2018/5/28.
 */
@Controller
public class HandleRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleRequest.class);

    /**
     * 更新资源
     */
    @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
    public void handlePutRequest(@ModelAttribute UserDTO userDTO) {
        LOGGER.info(userDTO.getName());
    }
}
