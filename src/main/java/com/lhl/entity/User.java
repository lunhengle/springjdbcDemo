package com.lhl.entity;

/**
 * Created by lenovo on 2016/4/19.
 * 用户实体类
 */
public class User {
    /**
     * id
     */
    private long id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
