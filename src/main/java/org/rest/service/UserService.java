package org.rest.service;

import org.rest.model.UserDTO;

/**
 * Created by XiuYin.Cui on 2018/5/23.
 */
public interface UserService {

    UserDTO getUserByName(String name);

    UserDTO saveUser(UserDTO userDTO);
}
