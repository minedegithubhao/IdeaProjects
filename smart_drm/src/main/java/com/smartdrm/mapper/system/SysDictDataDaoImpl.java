package com.smartdrm.mapper.system;

import com.smartdrm.entity.system.SysDictData;
import com.smartdrm.entity.system.SysUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/26 16:55
 */
@Repository
public class SysDictDataDaoImpl implements SysDictDataDao{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<SysDictData> getDataGrid(SysUserParam param) {
        return jdbcTemplate.query("select * from sys_dict_data", new BeanPropertyRowMapper<>(SysDictData.class));
    }

    @Override
    public int getDataGridCount(SysUserParam param) {
        return jdbcTemplate.queryForObject("select count(*) from sys_dict_data", Integer.class);
    }
}
