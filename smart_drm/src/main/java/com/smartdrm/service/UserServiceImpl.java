package com.smartdrm.service;

import com.smartdrm.common.AESUtils;
import com.smartdrm.common.EncryptUtils;
import com.smartdrm.common.OurException;
import com.smartdrm.entity.user.User;
import com.smartdrm.entity.user.UserParam;
import com.smartdrm.mapper.User.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author cxdpc
 * @date 2023-12-15 14:54
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUsers(UserParam param) {
        return userMapper.getUsers(param);
    }

    @Override
    public int getUserCount(UserParam param) {
        return userMapper.getUserCount(param);
    }

    @Override
    public void addUser(HttpServletRequest request, User user) throws RuntimeException {
        try {
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setLoginStatus(0);
            user.setPassword(AESUtils.getEncryptString(user.getPassword()));
            userMapper.insertUser(user);
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
    public void updateUser(User user) throws RuntimeException {
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException("更新用户异常");
        }
    }

    @Override
    public void login(String username, String password) {
        User user = userMapper.getUserByName(username);
        if (user == null){
            System.out.println("该用不存在");
            throw new OurException("请输入正确的账户/密码");
        }
        String decryptPassword = EncryptUtils.AESDecrypt(password);
        if (!AESUtils.getEncryptString(decryptPassword).equals(user.getPassword())){
            System.out.println("密码错误");
            throw new OurException("请输入正确的账户/密码");
        }
    }
}
