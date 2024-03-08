package com.smartdrm.controller.system;

import com.smartdrm.common.AjaxResult;
import com.smartdrm.common.OurException;
import com.smartdrm.entity.system.SysMenu;
import com.smartdrm.entity.system.SysMenuTree;
import com.smartdrm.entity.system.SysUser;
import com.smartdrm.service.system.SysMenuService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/2 09:18
 * @description 菜单管理
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController {

    private final Logger logger = Logger.getLogger(SysMenuController.class);

    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping("/index")
    public String role(){
        return "system/menu";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public AjaxResult dataGrid(){
        try {
            List<SysMenu> list = sysMenuService.getDataGrid();
            int count = sysMenuService.getDataGridCount();
            return AjaxResult.success(list, count);
        } catch (OurException e){
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error("查询失败!");
        }
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public AjaxResult getMenu(){
        try {
            Subject subject = SecurityUtils.getSubject();
            SysUser principal = (SysUser)subject.getPrincipal();
            List<SysMenuTree> list = sysMenuService.getMenu(principal.getLoginName());
            return AjaxResult.success("查询成功",list);
        } catch (OurException e){
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error("查询失败!");
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(@RequestBody SysMenu sysMenu){
        try {
            sysMenuService.save(sysMenu);
            return AjaxResult.success("保存成功");
        } catch (OurException e){
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error("保存失败!");
        }
    }

    /**
     * 查询菜单
     * @return
     */
    @RequestMapping("getMenuCombobox")
    @ResponseBody
    public AjaxResult getMenuCombobox(){
        try {
            List<SysMenu> list = sysMenuService.getDataGrid();
            return AjaxResult.success("查询成功",list);
        } catch (OurException e){
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error("查询失败!");
        }

    }

}
