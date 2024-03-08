package com.smartdrm.entity.system;

import lombok.Data;

import java.util.List;

/**
 * @author cxdpc
 * @date 2024/1/4 15:44
 */
@Data
public class SysMenuTree {

    private String text;

    private String iconCls;

    private String url;

    private List<SysMenuTree> children;
}
