package com.smartdrm.service;

import com.smartdrm.common.AESUtils;
import com.smartdrm.common.EncryptUtils;
import com.smartdrm.common.OurException;
import com.smartdrm.entity.SysUser;
import com.smartdrm.entity.UserParam;
import com.smartdrm.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 14:54
 */
@Service
public class UserServiceImpl implements UserService{

    static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<SysUser> getUsers(UserParam param) {
        return userMapper.getUsers(param);
    }

    @Override
    public int getUserCount(UserParam param) {
        return userMapper.getUserCount(param);
    }

    @Override
    public void addUser(HttpServletRequest request, SysUser sysUser) throws RuntimeException {
        try {
//            sysUser.setId(UUID.randomUUID().toString().replace("-", ""));
//            sysUser.setLoginStatus(0);
//            sysUser.setPassword(AESUtils.getEncryptString(sysUser.getPassword()));
            userMapper.insertUser(sysUser);
        } catch (Exception e) {
            throw new RuntimeException("新增用户异常");
        }
    }

    @Override
    public void deleteUserById(String id) throws RuntimeException {
        try {
            int result = userMapper.deleteUserById(id);
        } catch (Exception e) {
            throw new RuntimeException("删除用户异常");
        }
    }

    @Override
    public void updateUser(SysUser sysUser) throws RuntimeException {
        try {
            userMapper.updateUser(sysUser);
        } catch (Exception e) {
            throw new RuntimeException("更新用户异常");
        }
    }

    @Override
    public void login(String loginName, String password) {
        SysUser sysUser = userMapper.getUserByLoginName(loginName);
        if (sysUser == null){
            logger.error("该用不存在");
            throw new OurException("请输入正确的账户/密码");
        }
        String decryptPassword = EncryptUtils.AESDecrypt(password);
        if (!AESUtils.getEncryptString(decryptPassword).equals(sysUser.getPassword())){
            logger.error("密码错误");
            throw new OurException("请输入正确的账户/密码");
        }
    }
}
