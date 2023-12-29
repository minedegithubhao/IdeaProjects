package com.smartdrm.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date  2023-12-15 13:44
 * @description 用户信息
 */
@Data
public class SysUser {

    /**用户ID*/
    private int userId;

    /**部门ID*/
    private int deptId;

    /**登录账号*/
    private String loginName;

    /**用户昵称*/
    private String userName;

    /**用户类型（00系统用户 01注册用户）*/
    private String userType;

    /**手机号码*/
    private String phonenumber;

    /**用户性别（0男 1女 2未知）*/
    private String sex;

    /**头像路径*/
    private String avatar;

    /**密码*/
    private String password;

    /**盐加密*/
    private String salt;

    /**帐号状态（0正常 1停用）*/
    private String status;

    /**删除标志（0代表存在 2代表删除）*/
    private String delFlag;

    /**最后登录IP*/
    private String loginIp;

    /**最后登录时间*/
    private LocalDateTime loginDate;

    /**密码最后更新时间*/
    private LocalDateTime pwdUpdateDate;

    /**创建者*/
    private String createBy;

    /**创建时间*/
    private LocalDateTime createTime;

    /**更新者*/
    private String updateBy;

    /**更新时间*/
    private LocalDateTime updateTime;

    /**备注*/
    private String remark;
}
