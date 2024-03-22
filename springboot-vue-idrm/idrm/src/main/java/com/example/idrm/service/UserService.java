package com.example.idrm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.idrm.entity.User;

/**
 * @author cxdpc
 * @date 2024/3/22 13:34
 */
public interface UserService extends IService<User> {

    User findUserByNo(String no);
}
