package com.lhl.dao;

import com.lhl.AbstractBaseTest;
import com.lhl.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 */
public class UserDaoTest extends AbstractBaseTest {
    @Autowired
    private IUserDao iUserDao;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 测试增加.
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("addServiceUserTest");
        user.setPassword("123456");
        iUserDao.addUser(user);
    }

    /**
     * 测试删除.
     */
    @Test
    public void testRemoveUser() {
        iUserDao.removeUser(1);
    }

    /**
     * 测试修改用户名.
     */
    @Test
    public void testModifyUsername() {
        iUserDao.modifyUsername("modifyUsername", 2);
    }

    /**
     * 测试修改密码.
     */
    @Test
    public void testModifyPassword() {
        iUserDao.modifyPassword("654321", 2);
    }

    /**
     * 测试根据用户id获取用户信息.
     */
    @Test
    public void testReadUserById() {
        //为了避免 查询结果是空 报错 先查询判断
        boolean b = iUserDao.readUserCountById(12);
        if (b) {
            User user = iUserDao.readUserById(1);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user.id=" + user.getId() + " >>>>>>>>>>>>>>>>>>> user.username=" + user.getUsername() + " >>>>>>>>>>>>>>>>>>>>> user.password=" + user.getPassword());
        }
    }

    /**
     * 获取所有的用户信息.
     */
    @Test
    public void testReadUser() {
        List<User> userList = iUserDao.readUser();
        for (User user : userList) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user.id=" + user.getId() + " >>>>>>>>>>>>>>>>>>> user.username=" + user.getUsername() + " >>>>>>>>>>>>>>>>>>>>> user.password=" + user.getPassword());
        }
    }

    /**
     * 测试根据用户id获取用户信息.
     */
    @Test
    public void testReadMapUserById() {
        //为了避免 查询结果是空 报错 先查询判断
        boolean b = iUserDao.readUserCountById(12);
        if (b) {
            Map<String, Object> userMap = iUserDao.readMapUserById(12);
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user.id=" + userMap.get("id") + " >>>>>>>>>>>>>>>>>>> user.username=" + userMap.get("username") + " >>>>>>>>>>>>>>>>>>>>> user.password=" + userMap.get("password"));
        }
    }

    /**
     * 获取所有的用户信息.
     */
    @Test
    public void testReadMapUser() {
        List<Map<String, Object>> userList = iUserDao.readMapUser();
        for (Map<String, Object> userMap : userList) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user.id=" + userMap.get("id") + " >>>>>>>>>>>>>>>>>>> user.username=" + userMap.get("username") + " >>>>>>>>>>>>>>>>>>>>> user.password=" + userMap.get("password"));
        }
    }
}
