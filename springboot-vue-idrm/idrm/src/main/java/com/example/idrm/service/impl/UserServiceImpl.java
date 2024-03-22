package com.example.idrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.idrm.entity.User;
import com.example.idrm.service.UserService;
import com.example.idrm.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cxdpc
 * @date 2024/3/22 13:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByNo(String no) {
        return userMapper.findUserByNo(no);
    }
}
