package com.smartdrm.controller.system;

import com.smartdrm.entity.common.AjaxResult;
import com.smartdrm.entity.user.User;
import com.smartdrm.entity.user.UserParam;
import com.smartdrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return AjaxResult.success(users, users.size());
    }
}
