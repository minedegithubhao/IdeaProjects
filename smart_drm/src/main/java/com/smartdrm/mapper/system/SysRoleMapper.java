package com.smartdrm.mapper.system;

import com.smartdrm.entity.system.SysRole;
import com.smartdrm.entity.system.SysRoleParam;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023/12/29 14:10
 */
public interface SysRoleMapper {

    /**
     * 角色管理dataGrid
     * @param param 查询条件
     * @return 角色信息
     */
    List<SysRole> getDataGrid(SysRoleParam param);

    /**
     * dataGrid数量
     * @param param 查询条件
     * @return 数量
     */
    int getDataGridCount(SysRoleParam param);

    /**
     * 保存角色
     * @param sysRole 角色信息
     */
    void save(SysRole sysRole);

    /**
     * 根据id查询
     * @param roleId 角色id
     * @return 角色信息
     */
    SysRole getRoleById(int roleId);

    /**
     * 更新
     * @param sysRole 角色信息
     */
    void update(SysRole sysRole);

    /**
     * 根据id删除
     * @param roleId
     */
    void remove(int roleId);
}
