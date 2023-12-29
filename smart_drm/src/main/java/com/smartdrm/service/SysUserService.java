package com.smartdrm.service;

import com.smartdrm.entity.SysUser;
import com.smartdrm.entity.SysUserParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 14:53
 */
public interface SysUserService {

    /**
     * 根据id查询用户信息
     * @param id 主键
     * @return 用户信息
     */
    SysUser getUserById(String userId);

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return List<用户信息>
     */
    List<SysUser> getUsers(SysUserParam param);

    /**
     * 分页查询用户数量
     * @param param 查询条件
     * @return 数量
     */
    int getUserCount(SysUserParam param);

    /**
     * 新增用户
     * @param request request
     * @param sysUser 用户信息
     */
    void addUser(HttpServletRequest request, SysUser sysUser) throws RuntimeException;

    /**
     * 根据id删除用户
     * @param id id
     */
    void deleteUserById(String id) throws RuntimeException;

    /**
     * 更新用户
     * @param sysUser 用户信息
     */
    void updateUser(SysUser sysUser) throws RuntimeException;

    void login(String loginName, String password);
}
