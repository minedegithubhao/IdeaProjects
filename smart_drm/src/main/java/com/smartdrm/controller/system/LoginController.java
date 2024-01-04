package com.smartdrm.controller.system;

import com.smartdrm.common.*;
import com.smartdrm.entity.system.SysUser;
import com.smartdrm.service.system.SysUserService;
import org.apache.log4j.Logger;
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

    private static final Logger logger =  Logger.getLogger(LoginController.class);

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
            SysUser loginUser = userService.getUserByLoginName(loginName);
            String decryptPassword = EncryptUtils.AESDecrypt(password);
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",  loginUser);
            if (loginUser == null){
                logger.error("用户不存在");
                throw new OurException("用户不存在");
            }
            if (!AESUtils.getEncryptString(decryptPassword).equals(loginUser.getPassword())){
                logger.error("密码错误");
                throw new OurException("请输入正确的账户/密码");
            }
        } catch (OurException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e){
            logger.error("登录异常", e);
            return AjaxResult.error("服务异常，请联系管理员");
        }
        return AjaxResult.success("登录成功");
    }

}
