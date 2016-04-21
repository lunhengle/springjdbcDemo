package com.lhl.service.impl;

import com.lhl.dao.IUserDao;
import com.lhl.entity.User;
import com.lhl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2016/4/19.
 * 用户 service 接口实现类
 */

/**
 * 同一个类中调用事务不起作用
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    /**
     * 修改用户名.
     *
     * @param username 用户名
     * @param id       用户ID
     */
    private void modifyUsername(String username, long id) {
        iUserDao.modifyUsername(username, id);
    }


    /**
     * 修改用户名.
     *
     * @param username 用户名
     * @param id       用户ID
     */
    private void odifyUsername(String username, long id) {
        iUserDao.modifyUsername(username, id);
    }

    /**
     * 修改密码.
     *
     * @param password 用户密码
     * @param id       用户ID
     */
    private void modifyPassword(String password, long id) {
        iUserDao.modifyPassword(password, id);
    }


    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    public void modifyUser(String username, String password, long id) {
        this.odifyUsername(username, id);
        this.modifyPassword(password, id);
    }

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    public void odifyUser(String username, String password, long id) {
        this.odifyUsername(username, id);
        this.modifyPassword(password, id);
    }

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    @Transactional
    public void odifyUser1(String username, String password, long id) {
        this.odifyUsername(username, id);
        this.modifyPassword(password, id);
    }

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    @Transactional(readOnly = true)
    public void modifyUser1(String username, String password, long id) {
        this.odifyUsername(username, id);
        this.modifyPassword(password, id);
    }

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     * @return 返回用户数据
     */

    public void modifyUser2(String username, String password, long id) {
        this.modifyUsername(username, id);
        //设置程序错误
        /*int i = 100 / 0;*/
        this.modifyPassword(password, id);
    }

    /**
     * 修改.
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param id       用户ID
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyUser3(String username, String password, long id) {
        iUserDao.modifyUsername(username, id);
        int i = 100 / 0;
        iUserDao.modifyPassword(password, id);
    }

    /**
     * 先读取在修改.
     *
     * @param username 用户名称
     * @param id       用户ID
     * @return 用户列表
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> readAndModifyUser(String username, long id) {
        List<User> userList = iUserDao.readUser();
        iUserDao.modifyUsername(username, id);
        return userList;
    }

}
