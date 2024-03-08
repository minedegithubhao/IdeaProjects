package com.canbe.service.impl;

import com.canbe.dao.UserDao;
import com.canbe.domain.SysUser;
import com.canbe.service.SysUserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cxdpc
 * @date 2024/2/1 09:00
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTran(Integer id) {
        SysUser sysUser = userDao.findById(3);
        sysUser.setDeptId(111);
        userDao.updateUser(sysUser);
        userDao.deleteUserById(4);
    }
}
