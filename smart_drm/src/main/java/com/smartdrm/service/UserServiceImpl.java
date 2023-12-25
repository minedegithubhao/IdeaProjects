package com.smartdrm.service;

import com.smartdrm.entity.User;
import com.smartdrm.mapper.User.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 14:54
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
}
