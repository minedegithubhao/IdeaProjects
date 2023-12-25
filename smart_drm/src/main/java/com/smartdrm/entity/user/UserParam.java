package com.smartdrm.entity.user;

/**
 * @author cxdpc
 * @date 2023/12/25 16:17
 * @description 用户管理查询条件
 */
public class UserParam {

    /**用户id*/
    private String id;

    /**用户名*/
    private String username;

    /**状态*/
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
