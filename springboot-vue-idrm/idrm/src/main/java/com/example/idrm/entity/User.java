package com.example.idrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxd
 * @since 2024-03-25
 */
@Getter
@Setter
@TableName("s_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @TableField("no")
    private String no;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别， 0-男，1-女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
