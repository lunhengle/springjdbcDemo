package com.lhl.dao.impl;

import com.lhl.dao.IUserDao;
import com.lhl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 * 用户信dao层
 */
@Repository
public class UserDaoImpl implements IUserDao {
    /**
     * 内部 mapper 映射类
     */
    static class UserRowMapper implements RowMapper<User> {
        private UserRowMapper() {
        }

        private final static UserRowMapper userRowMapper = new UserRowMapper();

        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }

        public static UserRowMapper init() {
            return userRowMapper;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 判断当前id 有值否.
     *
     * @param id 用户ID
     * @return 大于零 有值 否则没有值
     */
    @Transactional(readOnly = true)
    public boolean readUserCountById(long id) {
        String sql = "SELECT count(id) FROM user WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.queryForObject(sql, params, Integer.class) != 0;
    }

    /**
     * 根据 id 查找用户信息.
     *
     * @param id 用户ID
     * @return 返回用户信息
     */
    @Transactional(readOnly = true)
    public User readUserById(long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.queryForObject(sql, UserRowMapper.init(), params);
    }

    /**
     * 根据用户id 读取 用户信息.
     *
     * @param id 用户ID
     * @return Map用户
     */
    @Transactional(readOnly = true)
    public Map<String, Object> readMapUserById(long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.queryForMap(sql, params);
    }

    /**
     * 读取所有的用户信息.
     *
     * @return 返回所有的用户信息
     */
    @Transactional(readOnly = true,isolation = Isolation.DEFAULT)
    public List<User> readUser() {
        String sql = "SELECT * FROM user";
        return this.jdbcTemplate.query(sql, UserRowMapper.init());
    }

    /**
     * 读取用户map对象list.
     *
     * @return 用户map对象list.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Map<String, Object>> readMapUser() {
        String sql = "SELECT * FROM user";
        return this.jdbcTemplate.queryForList(sql);
    }

    /**
     * 增加 user.
     *
     * @param user 用户表
     */
    public void addUser(User user) {
        String sql = "INSERT INTO user (username,password) VALUES(?,?)";
        Object[] params = new Object[]{user.getUsername(), user.getPassword()};
        this.jdbcTemplate.update(sql, params);
    }

    /**
     * 删除用户.
     *
     * @param id 用户ID
     */
    public void removeUser(long id) {
        String sql = "DELETE FROM user WHERE id = ? ";
        Object[] params = new Object[]{id};
        this.jdbcTemplate.update(sql, params);
    }

    /**
     * 更新用户名称.
     *
     * @param username 用户名称
     * @param id       用户ID
     */
    @Transactional(propagation = Propagation.NESTED)
    public void modifyUsername(String username, long id) {
        String sql = "UPDATE user SET username=? WHERE id = ?";
        Object[] params = new Object[]{username, id};
        this.jdbcTemplate.update(sql, params);
    }

    /**
     * 更新用户密码.
     *
     * @param password 密码
     * @param id       用户ID
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyPassword(String password, long id) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        Object[] params = new Object[]{password, id};
        this.jdbcTemplate.update(sql, params);
    }


}
