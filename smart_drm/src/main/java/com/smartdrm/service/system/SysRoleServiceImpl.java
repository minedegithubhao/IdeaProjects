package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysRole;
import com.smartdrm.entity.system.SysRoleParam;
import com.smartdrm.mapper.system.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023/12/29 13:52
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getDataGrid(SysRoleParam param) {
        return sysRoleMapper.getDataGrid(param);
    }

    @Override
    public int getDataGridCount(SysRoleParam param) {
        return sysRoleMapper.getDataGridCount(param);
    }

    @Override
    public void save(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }

    @Override
    public SysRole getRoleById(int roleId) {
        return sysRoleMapper.getRoleById(roleId);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void remove(int roleId) {
        sysRoleMapper.remove(roleId);
    }
}
