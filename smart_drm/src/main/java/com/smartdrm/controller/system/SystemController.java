package com.smartdrm.controller.system;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.smartdrm.common.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cxdpc
 * @date 2023-12-22 13:23
 */
@RequestMapping("/system")
@Controller
public class SystemController {

    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/getRandCodeImage")
    public void getRandCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 125);

        //图形验证码写出,写出到流
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        try{
            lineCaptcha.write(response.getOutputStream());
            String code = lineCaptcha.getCode();
            // 将验证码存在在Session中
            request.getSession().setAttribute(CommonConstants.RAND_CODE, code);
            response.getOutputStream().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
