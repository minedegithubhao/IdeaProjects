package com.smartdrm.entity;

/**
 * @author cxdpc
 * @date 2023-12-15 13:44
 * @description 用户信息
 */
public class User {

    /**主键*/
    private String id;
    /**姓名*/
    private String username;
    /**密码*/
    private String password;
    /**状态，1：有效，0：失效*/
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
