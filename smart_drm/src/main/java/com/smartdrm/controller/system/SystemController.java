package com.smartdrm.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cxdpc
 * @date 2023-12-22 13:23
 */
@RequestMapping("/systemController")
@Controller
public class SystemController {

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
