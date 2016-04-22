package com.lhl.dao;

import com.lhl.entity.Person;
import com.lhl.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 * 用户dao接口
 */
public interface IUserDao {
    /**
     * 判断当前id 有值否.
     *
     * @param id 用户ID
     * @return 大于零 有值 否则没有值
     */
    boolean readUserCountById(long id);

    /**
     * 根据 id 查找用户信息.
     *
     * @param id 用户ID
     * @return 返回用户
     */
    User readUserById(long id);

    /**
     * 根据用户id 读取 用户信息.
     *
     * @param id 用户ID
     * @return Map用户
     */
    Map<String, Object> readMapUserById(long id);

    /**
     * 读取所有的用户信息.
     *
     * @return 返回所有的用户信息
     */
    List<User> readUser();


    /**
     * 读取用户map对象list.
     *
     * @return 用户map对象list.
     */
    List<Map<String, Object>> readMapUser();

    /**
     * 增加 user.
     *
     * @param user 用户表
     */
    void addUser(User user);

    /**
     * 删除用户.
     *
     * @param id 用户ID
     */
    void removeUser(long id);

    /**
     * 更新用户名称.
     *
     * @param username 用户名称
     * @param id       用户ID
     */
    void modifyUsername(String username, long id);

    /**
     * 更新用户密码.
     *
     * @param password 密码
     * @param id       用户ID
     */
    void modifyPassword(String password, long id);

    /**
     * 批量更新.
     *
     * @param list 用户列表
     */
    void modifyUserList(List<User> list);

    /**
     * 批量插入.
     *
     * @param list 用户列表
     */
    void addUserList(List<User> list);


}
