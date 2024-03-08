package com.canbe.dao;

import com.canbe.domain.SysUser;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/31 16:33
 */
public interface UserDao {

    void saveUser(SysUser user);
    void updateUser(SysUser user);
    void deleteUserById(Integer id);
    SysUser findById(Integer id);
    List<SysUser> findUserByName(String name);
    List<SysUser> findUserByNameBySQL(String name);
    List<SysUser> findUserByNameByQBC(String name);
}
