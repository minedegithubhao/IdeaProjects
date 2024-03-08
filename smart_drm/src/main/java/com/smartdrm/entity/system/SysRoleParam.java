package com.smartdrm.entity.system;

import com.smartdrm.common.CommonParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cxdpc
 * @date 2023/12/29 13:48
 * @description 角色查询条件
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleParam extends CommonParam {

    /**角色名称*/
    private String roleName;

    /**角色code*/
    private String roleKey;

    /**角色状态*/
    private String status;
}
