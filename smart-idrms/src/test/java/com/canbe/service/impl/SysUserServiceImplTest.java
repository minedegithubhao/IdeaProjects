package com.canbe.service.impl;

import com.canbe.dao.UserDao;
import com.canbe.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cxdpc
 * @date 2024/2/1 09:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@ActiveProfiles("dev")
public class SysUserServiceImplTest {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    UserDao userDao;

    @Test
    public void testTran() {
        sysUserService.testTran(1);
        System.out.println(userDao.findById(3));
        System.out.println(userDao.findById(4));
    }

    public static void main(String[] args) {
        System.out.println("中1235".matches("[0-9A-Za-z]{1,5}"));
    }
}