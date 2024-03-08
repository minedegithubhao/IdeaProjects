package com.smartdrm.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author cxdpc
 * @date 2024/1/12 16:08
 */
public class UserFormFilter extends FormAuthenticationFilter {

    @Override
    public void setUsernameParam(String usernameParam) {
        super.setUsernameParam(usernameParam);
    }

    @Override
    public void setPasswordParam(String passwordParam) {
        super.setPasswordParam(passwordParam);
    }

    /**认证成功时会调用*/
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("认证成功");
        return false;
    }

    /**认证失败时会调用*/
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("认证失败");
        return false;
    }
}
