package com.smartdrm.shiro;

import com.smartdrm.common.AESUtils;
import com.smartdrm.common.EncryptUtils;
import com.smartdrm.entity.system.SysUser;
import com.smartdrm.service.system.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cxdpc
 * @date 2024/1/12 09:26
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;


    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String loginName = token.getPrincipal().toString();
        SysUser sysUser = sysUserService.getUserByLoginName(loginName);
        if (sysUser != null && sysUser.getPassword().equals(String.valueOf(userToken.getPassword()))) {
            return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), this.getName());
        }
        return null;
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
