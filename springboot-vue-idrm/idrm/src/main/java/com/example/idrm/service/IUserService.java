package com.example.idrm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.idrm.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxd
 * @since 2024-03-25
 */
public interface IUserService extends IService<User> {

    Page<User> pageC(IPage<User> page, Wrapper<User> queryWrapper);
}
