package com.smartdrm.mapper.User;

import com.smartdrm.entity.user.User;
import com.smartdrm.entity.user.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-12-15 13:43
 */
@Mapper
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id 主键
     * @return 用户信息
     */
    User getUserById(String id);

    /**
     * 分页查询用户信息
     * @param param 查询条件
     * @return List<用户信息>
     */
    List<User> getUsers(UserParam param);

    /**
     * 分页查询用户数量
     * @param param 查询条件
     * @return 用户数量
     */
    int getUserCount(UserParam param);

    /**
     * 新增用户
     * @param user 用户信息
     */
    void insertUser(User user);

    /**
     * 根据id删除用户
     * @param id id
     * @return 无
     */
    int deleteUserById(String id);
}
