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

    @RequestMapping(value = {"/","/login"})
    public String login(){
        return "helloReader";
    }
}
