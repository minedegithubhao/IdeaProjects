package com.canbe.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date  2023-12-15 13:44
 * @description 用户信息
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser {

    /**用户ID*/
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    /**部门ID*/
    @Column(name = "dept_id")
    private int deptId;

    /**登录账号*/
    @Column(name = "login_name")
    private String loginName;

    /**用户昵称*/
    @Transient
    private String userName;

    /**用户类型（00系统用户 01注册用户）*/
    @Transient
    private String userType;

    /**手机号码*/
    @Transient
    private String phonenumber;

    /**用户性别（0男 1女 2未知）*/
    @Transient
    private String sex;

    /**头像路径*/
    @Transient
    private String avatar;

    /**密码*/
    @Transient
    private String password;

    /**盐加密*/
    @Transient
    private String salt;

    /**帐号状态（0正常 1停用）*/
    @Transient
    private String status;

    /**删除标志（0代表存在 2代表删除）*/
    @Transient
    private String delFlag;

    /**最后登录IP*/
    @Transient
    private String loginIp;

    /**最后登录时间*/
    @Transient
    private LocalDateTime loginDate;

    /**密码最后更新时间*/
    @Transient
    private LocalDateTime pwdUpdateDate;

    /**创建者*/
    @Transient
    private String createBy;

    /**创建时间*/
    @Transient
    private LocalDateTime createTime;

    /**更新者*/
    @Transient
    private String updateBy;

    /**更新时间*/
    @Transient
    private LocalDateTime updateTime;

    /**备注*/
    @Transient
    private String remark;

}
