package com.smartdrm.controller.system;

import com.smartdrm.entity.User;
import com.smartdrm.mapper.User.UserMapper;
import com.smartdrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ASUS
 * @version 1.0
 * @description: TODO
 * @date 2023/11/13 21:11
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/login")
    public boolean login(String username){
        User user = userService.getUserById("001");
        System.out.println("登陆成功");
        return true;
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @RequestMapping("/role")
    public String role(){
        return "role";
    }
}
