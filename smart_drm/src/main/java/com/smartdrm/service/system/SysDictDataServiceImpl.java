package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysDictData;
import com.smartdrm.entity.system.SysDictType;
import com.smartdrm.entity.system.SysUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/26 15:49
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<SysDictType> getDataGrid(SysUserParam param) {
        return jdbcTemplate.query("select * from sys_dict_type", new BeanPropertyRowMapper<>(SysDictType.class));
    }

    @Override
    public int getDataGridCount(SysUserParam param) {
        return jdbcTemplate.queryForObject("select count(*) from sys_dict_type", Integer.class);
    }

    @Override
    public List<SysDictData> dictDataGrid(String dictType) {
        return jdbcTemplate.query("select * from sys_dict_data where dict_type = ?", Arrays.asList(dictType).toArray() ,new BeanPropertyRowMapper<>(SysDictData.class));
    }

    @Override
    public int getDictDataGridCount(String dictType) {
        return jdbcTemplate.queryForObject("select count(*) from sys_dict_data where dict_type = ?", Arrays.asList(dictType).toArray(), Integer.class);
    }
}
