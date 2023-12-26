package com.smartdrm.controller.system;

import com.smartdrm.entity.common.AjaxResult;
import com.smartdrm.entity.user.User;
import com.smartdrm.entity.user.UserParam;
import com.smartdrm.service.UserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/userController")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public String user(){
        return "user/user";
    }

    @RequestMapping("/query")
    @ResponseBody
    public AjaxResult query(UserParam param){
        List<User> users = userService.getUsers(param);
        int userCount = userService.getUserCount(param);
        return AjaxResult.success(users, userCount);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public AjaxResult add(HttpServletRequest request, @RequestBody User user){
        try {
            userService.addUser(request, user);
            return AjaxResult.success("保存成功");

        } catch (RuntimeException e) {
            return AjaxResult.error("保存失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult delete(HttpServletRequest request, String id){
        try {
            userService.deleteUserById(id);
            return AjaxResult.success("删除成功");
        } catch (Exception e) {
            return AjaxResult.error("删除失败");
        }
    }
}
