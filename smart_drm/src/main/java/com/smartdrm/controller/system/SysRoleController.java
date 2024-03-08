package com.smartdrm.controller.system;

import com.smartdrm.common.AjaxResult;
import com.smartdrm.common.OurException;
import com.smartdrm.entity.system.SysRole;
import com.smartdrm.entity.system.SysRoleParam;
import com.smartdrm.service.system.SysRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-22 14:52
 */
@RequestMapping("/system/role")
@Controller
public class SysRoleController {

    private final Logger logger = Logger.getLogger(SysRoleController.class);

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("/index")
    public String role(){
        return "system/role";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public AjaxResult getDataGrid(SysRoleParam param){
        List<SysRole> sysRoleList = sysRoleService.getDataGrid(param);
        int dataGridCount = sysRoleService.getDataGridCount(param);
        return AjaxResult.success(sysRoleList, dataGridCount);
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(@RequestBody SysRole sysRole){
        try {
            sysRoleService.save(sysRole);
            return AjaxResult.success();
        } catch (OurException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            return AjaxResult.error("服务异常，请联系管理员!");
        }
    }

    @RequestMapping("/getRoleById")
    @ResponseBody
    public AjaxResult getRoleById(int roleId){
        try {
            SysRole sysRole = sysRoleService.getRoleById(roleId);
            return AjaxResult.success("查询成功", sysRole);
        } catch (OurException e) {
            logger.error(e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            logger.error(e);
            return AjaxResult.error("服务异常，请联系管理员!");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody SysRole sysRole){
        try {
            sysRoleService.update(sysRole);
            return AjaxResult.success();
        } catch (OurException e) {
            logger.error(e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            logger.error(e);
            return AjaxResult.error("服务异常，请联系管理员!");
        }
    }

    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(int roleId){
        try {
            sysRoleService.remove(roleId);
            return AjaxResult.success();
        } catch (OurException e) {
            logger.error(e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            logger.error(e);
            return AjaxResult.error("服务异常，请联系管理员!");
        }
    }
}
