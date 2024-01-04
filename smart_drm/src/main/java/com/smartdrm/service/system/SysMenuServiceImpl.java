package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysMenu;
import com.smartdrm.entity.system.SysMenuTree;
import com.smartdrm.mapper.system.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<SysMenuTree> getMenu() {
        // 一级菜单
        List<SysMenuTree> sysMenuTreeList = new ArrayList<>();
        List<SysMenu> menus = sysMenuMapper.getMenu("M");
        for (SysMenu menu : menus) {
            SysMenuTree sysMenuTree = new SysMenuTree();
            sysMenuTree.setText(menu.getMenuName());
            sysMenuTree.setIconCls(menu.getIcon());

            List<SysMenu> menuByParentId = sysMenuMapper.getMenuByParentId(menu.getMenuId());
            List<SysMenuTree> sysMenuTreeChildrenList = new ArrayList<>();
            for (SysMenu sysMenu : menuByParentId) {
                SysMenuTree sysMenuTreeChildren = new SysMenuTree();
                sysMenuTreeChildren.setUrl(sysMenu.getUrl());
                sysMenuTreeChildren.setText(sysMenu.getMenuName());
                sysMenuTreeChildren.setIconCls(sysMenu.getIcon());
                sysMenuTreeChildrenList.add(sysMenuTreeChildren);
            }

            sysMenuTree.setChildren(sysMenuTreeChildrenList);
            sysMenuTreeList.add(sysMenuTree);
        }
        return sysMenuTreeList;
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
