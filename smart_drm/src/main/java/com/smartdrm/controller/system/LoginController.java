package com.smartdrm.controller.system;

import com.smartdrm.common.CommonConstants;
import com.smartdrm.entity.common.AESUtils;
import com.smartdrm.entity.common.AjaxResult;
import com.smartdrm.entity.common.EncryptUtils;
import com.smartdrm.entity.user.User;
import com.smartdrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ASUS
 * @version 1.0
 * @description: TODO
 * @date 2023/11/13 21:11
 */
@RequestMapping("/loginController")
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, String username, String password, String randCode){
        //  User user = userService.getUserById(username);
        String decryptPassword = EncryptUtils.AESDecrypt(password);
        // 从session获取验证码
        String randCodeSession = String.valueOf(request.getSession().getAttribute(CommonConstants.RAND_CODE));
        if (!randCode.equalsIgnoreCase(randCodeSession)){
            return AjaxResult.error("验证码错误");
        }
        return AjaxResult.success("登录成功");
    }

}
