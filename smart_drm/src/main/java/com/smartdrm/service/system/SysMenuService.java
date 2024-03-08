package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysMenu;
import com.smartdrm.entity.system.SysMenuTree;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/2 09:23
 */
public interface SysMenuService {
    List<SysMenu> getDataGrid();

    int getDataGridCount();

    List<SysMenuTree> getMenu(String loginName);

    void save(SysMenu sysMenu);
}
