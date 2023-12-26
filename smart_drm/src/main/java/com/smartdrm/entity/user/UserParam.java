package com.smartdrm.entity.user;

import com.smartdrm.entity.common.CommonParam;

/**
 * @author cxdpc
 * @date 2023/12/25 16:17
 * @description 用户管理查询条件
 */
public class UserParam extends CommonParam {

    /**用户名*/
    private String username;

    private String realname;

    /**状态*/
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
