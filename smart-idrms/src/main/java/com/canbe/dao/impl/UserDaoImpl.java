package com.canbe.dao.impl;

import com.canbe.dao.UserDao;
import com.canbe.domain.SysUser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/31 16:33
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    HibernateTemplate hibernateTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveUser(SysUser user) {
        hibernateTemplate.save(user);
    }

    @Override
    public void updateUser(SysUser user) {
        hibernateTemplate.update(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        jdbcTemplate.update("delete from sys_user where user_id = ?", new Object[]{id});
    }

    @Override
    public SysUser findById(Integer id) {
//        List<SysUser> query = jdbcTemplate.query("select * from sys_user where user_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(SysUser.class));
//        System.out.println(query.size());
        return hibernateTemplate.get(SysUser.class, id);
    }

    @Override
    public List<SysUser> findUserByName(String loginName) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createQuery("from SysUser where loginName = :loginName");
        Query queryTemp = query.setParameter("loginName",loginName);
        return queryTemp.getResultList();
    }

    @Override
    public List<SysUser> findUserByNameBySQL(String name) {
        return null;
    }

    @Override
    public List<SysUser> findUserByNameByQBC(String name) {
        return null;
    }
}
