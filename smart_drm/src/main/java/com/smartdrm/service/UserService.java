package com.smartdrm.service;

import com.smartdrm.entity.User;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 14:53
 */
public interface UserService {

    User getUserById(String id);

    List<User> getUsers();
}
