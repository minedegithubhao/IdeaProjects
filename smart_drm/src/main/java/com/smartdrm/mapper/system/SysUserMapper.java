package com.smartdrm.mapper.system;

import com.smartdrm.entity.system.SysUser;
import com.smartdrm.entity.system.SysUserParam;
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
    SysUser getUserById(String userId);

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return List<用户信息>
     */
    List<SysUser> getDataGrid(SysUserParam param);

    /**
     * 分页查询用户数量
     * @param param 查询条件
     * @return 用户数量
     */
    int getDataGridCount(SysUserParam param);

    /**
     * 新增用户
     * @param sysUser 用户信息
     */
    void save(SysUser sysUser);

    /**
     * 根据id删除用户
     * @param id id
     * @return 无
     */
    int remove(String id);

    /**
     * 更新用户信息
     * @param sysUser 用户信息
     */
    void update(SysUser sysUser);


}
