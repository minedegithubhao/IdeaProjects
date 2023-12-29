package com.smartdrm.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date 2023/12/29 13:53
 * @description 角色实体类
 */
@Data
public class SysRole {

    /**id*/
    private int roleId;

    /**角色名称*/
    private String roleName;

    /**角色code*/
    private String roleKey;

    /**显示顺序*/
    private int roleSort;

    /**数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）*/
    private String dataScope;

    /**角色状态（0正常 1停用）*/
    private String status;

    /**删除标志（0代表存在 2代表删除）*/
    private String delFlag;

    /**创建人*/
    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    /**备注*/
    private String remark;
}
