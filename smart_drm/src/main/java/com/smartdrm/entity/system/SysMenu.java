package com.smartdrm.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date 2024/1/2 09:24
 * @description 菜单实体类
 */
@Data
public class SysMenu {

    /**菜单Id*/
    private int menuId;

    /**菜单名称*/
    private String menuName;

    /**父菜单ID*/
    private int parentId;

    /**显示顺序*/
    private int orderNum;

    /**请求地址*/
    private String url;

    /**打开方式（menuItem页签 menuBlank新窗口）*/
    private String target;

    /**菜单类型（M目录 C菜单 F按钮）*/
    private String menuType;

    /**菜单状态（0显示 1隐藏）*/
    private String visible;

    /**是否刷新（0刷新 1不刷新）*/
    private String isRefresh;

    /**权限标识*/
    private String perms;

    /**菜单图标*/
    private String icon;

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
