package com.smartdrm.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cxdpc
 * @date 2023-12-22 11:10
 */
@RequestMapping("/userController")
@Controller
public class UserController {

    @RequestMapping("/user")
    public String user(){
        return "user";
    }
}
