package com.smartdrm.controller.system;

import com.smartdrm.common.*;
import com.smartdrm.service.system.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ASUS
 * @version 1.0
 * @description: TODO
 * @date 2023/11/13 21:11
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    SysUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, String loginName, String password, String randCode) {
        // 从session获取验证码
        String randCodeSession = String.valueOf(request.getSession().getAttribute(CommonConstants.RAND_CODE));
        if (!randCode.equalsIgnoreCase(randCodeSession)) {
            return AjaxResult.error("验证码错误");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginName,password);
            subject.login(token);
        } catch (OurException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            return AjaxResult.error("请输入正确的账户/密码");
        }
        return AjaxResult.success("登录成功");
    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request){
//        return "redirect:/system/login";
//    }

}
