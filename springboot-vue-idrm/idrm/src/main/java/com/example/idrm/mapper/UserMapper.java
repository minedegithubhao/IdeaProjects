package com.example.idrm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idrm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxd
 * @since 2024-03-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from s_user ${ew.customSqlSegment}")
    Page<User> pageC(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
