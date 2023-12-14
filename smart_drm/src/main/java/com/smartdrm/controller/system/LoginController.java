package com.smartdrm.controller.system;

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
