package com.smartdrm.service.system;

import com.smartdrm.common.AESUtils;
import com.smartdrm.common.EncryptUtils;
import com.smartdrm.common.OurException;
import com.smartdrm.entity.system.SysUser;
import com.smartdrm.entity.system.SysUserParam;
import com.smartdrm.mapper.system.SysUserMapper;
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
public class SysUserServiceImpl implements SysUserService {

    static final Logger logger = Logger.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getUserById(String userId) {
        SysUser sysUser = userMapper.getUserById(userId);
        sysUser.setPassword(AESUtils.getDecryptString(sysUser.getPassword()));
        return sysUser;
    }

    @Override
    public List<SysUser> getDataGrid(SysUserParam param) {
        return userMapper.getDataGrid(param);
    }

    @Override
    public int getDataGridCount(SysUserParam param) {
        return userMapper.getDataGridCount(param);
    }

    @Override
    public void save(SysUser sysUser) throws RuntimeException {
        try {
            userMapper.save(sysUser);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("新增用户异常");
        }
    }

    @Override
    public void remove(String userId) throws RuntimeException {
        try {
            int result = userMapper.remove(userId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("删除用户异常");
        }
    }

    @Override
    public void update(SysUser sysUser) throws RuntimeException {
        try {
            sysUser.setPassword(AESUtils.getEncryptString(sysUser.getPassword()));
            userMapper.update(sysUser);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("更新用户异常");
        }
    }

    @Override
    public SysUser getUserByLoginName(String loginName) {
        try {
            return userMapper.getUserByLoginName(loginName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OurException("登录失败");
        }
    }
}
