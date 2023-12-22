package com.smartdrm.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cxdpc
 * @date 2023-12-22 14:52
 */
@RequestMapping("/roleController")
@Controller
public class RoleController {

    @RequestMapping("/role")
    public String role(){
        return "role";
    }
}
