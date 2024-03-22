package com.example.idrm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.idrm.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cxdpc
 * @date 2024/3/22 13:33
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findUserByNo(String no);
}
