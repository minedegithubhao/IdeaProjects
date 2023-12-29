package com.smartdrm.mapper;

import com.smartdrm.entity.SysUser;
import com.smartdrm.entity.SysUserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 13:43
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询用户
     * @param loginName 用户名
     * @return 用户信息
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 根据id查询用户
     * @param id 主键
     * @return 用户信息
     */
    SysUser getUserById(String id);

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return List<用户信息>
     */
    List<SysUser> getUsers(SysUserParam param);

    /**
     * 分页查询用户数量
     * @param param 查询条件
     * @return 用户数量
     */
    int getUserCount(SysUserParam param);

    /**
     * 新增用户
     * @param sysUser 用户信息
     */
    void insertUser(SysUser sysUser);

    /**
     * 根据id删除用户
     * @param id id
     * @return 无
     */
    int deleteUserById(String id);

    /**
     * 更新用户信息
     * @param sysUser 用户信息
     */
    void updateUser(SysUser sysUser);


}
