package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysRole;
import com.smartdrm.entity.system.SysRoleParam;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023/12/29 13:52
 */
public interface SysRoleService {


    /**
     * 查询
     * @param param 查询参数
     * @return 角色
     */
    List<SysRole> getDataGrid(SysRoleParam param);

    /**
     * dataGrid查询count
     * @param param 查询参数
     * @return 数量
     */
    int getDataGridCount(SysRoleParam param);

    /**
     * 保存
     * @param sysRole 角色
     */
    void save(SysRole sysRole);

    /**
     * 查询
     * @param roleId roleId
     * @return 角色信息
     */
    SysRole getRoleById(int roleId);

    /**
     * 更新
     * @param sysRole 更新角色
     */
    void update(SysRole sysRole);

    /**
     * 根据id删除
     * @param roleId id
     */
    void remove(int roleId);
}
