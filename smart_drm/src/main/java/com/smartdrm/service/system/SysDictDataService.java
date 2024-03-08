package com.smartdrm.service.system;

import com.smartdrm.entity.system.SysDictData;
import com.smartdrm.entity.system.SysDictType;
import com.smartdrm.entity.system.SysUserParam;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/26 15:49
 */
public interface SysDictDataService {
    List<SysDictType> getDataGrid(SysUserParam param);

    int getDataGridCount(SysUserParam param);

    List<SysDictData> dictDataGrid(String dictType);

    int getDictDataGridCount(String dictType);
}
