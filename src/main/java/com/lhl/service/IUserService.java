package com.lhl.service;

import com.lhl.entity.User;

import java.util.List;

/**
 * Created by lenovo on 2016/4/19.
 * 返回用户信息service 接口
 */
public interface IUserService {
    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void modifyUser(String username, String password, long id);

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void odifyUser(String username, String password, long id);

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void odifyUser1(String username, String password, long id);

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void modifyUser1(String username, String password, long id);

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void modifyUser2(String username, String password, long id);

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    void modifyUser3(String username, String password, long id);

    /**
     * 先读取在修改.
     *
     * @param username 用户名称
     * @param id       用户ID
     * @return 用户列表
     */
    List<User> readAndModifyUser(String username, long id);

}
