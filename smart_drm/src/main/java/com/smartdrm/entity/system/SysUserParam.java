package com.smartdrm.entity.system;

import com.smartdrm.common.CommonParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cxdpc
 * @date 2023/12/25 16:17
 * @description 用户管理查询条件
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserParam extends CommonParam {

    /**登录名称*/
    private String loginName;

    /**用户名称*/
    private String userName;

    /**状态*/
    private String status;

}

