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
     * @return User
     */
    User getUserById(String id);

    List<User> getUsers(UserParam param);
}
