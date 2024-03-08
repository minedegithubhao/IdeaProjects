package com.smartdrm.mapper.system;

import com.smartdrm.entity.system.SysMenu;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/2 09:36
 */
public interface SysMenuMapper {
    List<SysMenu> getDataGrid();

    int getDataGridCount();

    List<SysMenu> getAllMenu();

    List<SysMenu> getMenu(String loginName);

    void save(SysMenu sysMenu);

    Integer getMaxMenuIdParentId(int parentId);

    List<SysMenu> getMenuByParentId(int parentId);
}
