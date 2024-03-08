package com.smartdrm.mapper.system;

import com.smartdrm.entity.system.SysDictData;
import com.smartdrm.entity.system.SysUserParam;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/26 16:54
 */
public interface SysDictDataDao {


    List<SysDictData> getDataGrid(SysUserParam param);

    int getDataGridCount(SysUserParam param);
}
