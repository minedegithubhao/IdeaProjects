package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysMenu;
import com.smartdrm.entity.system.SysMenuTree;
import com.smartdrm.mapper.system.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public List<SysMenuTree> getMenu(String loginName) {
        // 一级菜单
        List<SysMenuTree> sysMenuTreeList = new ArrayList<>();
        List<SysMenu> menus = null;
        if ("admin".equals(loginName)){
            menus = sysMenuMapper.getAllMenu();
        }else {
            menus = sysMenuMapper.getMenu(loginName);

        }
        List<SysMenu> parentMenus = menus.stream()
                .filter(menu -> "M".equals(menu.getMenuType()))
                .collect(Collectors.toList());
        List<SysMenu> subMenus = menus.stream()
                .filter(menu -> "C".equals(menu.getMenuType())).
                collect(Collectors.toList());

        for (SysMenu menu : parentMenus) {
            SysMenuTree sysMenuTree = new SysMenuTree();
            sysMenuTree.setText(menu.getMenuName());
            sysMenuTree.setIconCls(menu.getIcon());

            List<SysMenuTree> sysMenuTreeChildrenList = new ArrayList<>();
            for (SysMenu sysMenu : subMenus) {
                if (sysMenu.getParentId() != (menu.getMenuId())){
                    continue;
                }
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
