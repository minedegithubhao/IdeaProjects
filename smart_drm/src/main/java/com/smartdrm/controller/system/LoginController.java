package com.smartdrm.controller.system;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ASUS
 * @version 1.0
 * @description: TODO
 * @date 2023/11/13 21:11
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String login(String username){
        return "index";
    }

}
