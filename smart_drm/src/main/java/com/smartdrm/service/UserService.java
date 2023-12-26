package com.smartdrm.service;

import com.smartdrm.entity.user.User;
import com.smartdrm.entity.user.UserParam;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 14:53
 */
public interface UserService {

    /**
     * 根据id查询用户信息
     * @param id 主键
     * @return 用户信息
     */
    User getUserById(String id);

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return List<用户信息>
     */
    List<User> getUsers(UserParam param);

    /**
     * 分页查询用户数量
     * @param param 查询条件
     * @return 数量
     */
    int getUserCount(UserParam param);
}
