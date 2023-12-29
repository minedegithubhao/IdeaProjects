package com.smartdrm.controller.system;

import com.smartdrm.common.AjaxResult;
import com.smartdrm.entity.system.SysUser;
import com.smartdrm.entity.system.SysUserParam;
import com.smartdrm.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-22 11:10
 * @description 用户管理
 *
 */
@RequestMapping("/system/user")
@Controller
public class SysUserController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/index")
    public String user(){
        return "system/user";
    }

    @RequestMapping("/getDataGrid")
    @ResponseBody
    public AjaxResult getDataGrid(SysUserParam param){
        List<SysUser> sysUsers = sysUserService.getDataGrid(param);
        int userCount = sysUserService.getDataGridCount(param);
        return AjaxResult.success(sysUsers, userCount);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, @RequestBody SysUser sysUser){
        try {
            sysUserService.addUser(request, sysUser);
            return AjaxResult.success("保存成功");

        } catch (RuntimeException e) {
            return AjaxResult.error("保存失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(HttpServletRequest request, String userId){
        try {
            sysUserService.deleteUserById(userId);
            return AjaxResult.success("删除成功");
        } catch (Exception e) {
            return AjaxResult.error("删除失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(HttpServletRequest request, @RequestBody SysUser sysUser){
        try {
            sysUserService.updateUser(sysUser);
            return AjaxResult.success("更新成功");
        } catch (Exception e) {
            return AjaxResult.error("更新失败");
        }
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public AjaxResult getUserById(HttpServletRequest request, String userId){
        try {
            SysUser sysUser = sysUserService.getUserById(userId);
            return AjaxResult.success("获取成功", sysUser);
        } catch (Exception e) {
            return AjaxResult.error("获取失败");
        }
    }
}
