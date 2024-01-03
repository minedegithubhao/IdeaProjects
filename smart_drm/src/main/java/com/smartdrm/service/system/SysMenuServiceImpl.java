package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysMenu;
import com.smartdrm.mapper.system.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/2 09:23
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getDataGrid() {
        return sysMenuMapper.getDataGrid();
    }

    @Override
    public int getDataGridCount() {
        return sysMenuMapper.getDataGridCount();
    }

    @Override
    public List<SysMenu> getMenu() {
        return sysMenuMapper.getMenu("M");
    }

    @Override
    public void save(SysMenu sysMenu) {
        Integer maxMenuIdParentId = sysMenuMapper.getMaxMenuIdParentId(sysMenu.getParentId());
        if (maxMenuIdParentId == null){
            maxMenuIdParentId = sysMenu.getParentId() * 100;
        } else {
            maxMenuIdParentId = maxMenuIdParentId + 1;
        }
        sysMenu.setMenuId(maxMenuIdParentId);
        sysMenu.setCreateBy("admin");
        sysMenuMapper.save(sysMenu);
    }
}
