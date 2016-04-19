package com.lhl.dao.impl;

import com.lhl.dao.IPersonDao;
import com.lhl.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 * 人dao 实现类
 */
@Repository
public class PersonDaoImpl implements IPersonDao {
    /**
     * 内部 mapper 映射类
     */
    static class PersonRowMapper implements RowMapper<Person> {
        private PersonRowMapper() {
        }

        private final static PersonRowMapper personRowMapper = new PersonRowMapper();

        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setEmail(resultSet.getString("email"));
            person.setPhone(resultSet.getString("phone"));
            person.setAddress(resultSet.getString("address"));
            person.setEmail(resultSet.getString("email"));
            return person;
        }

        public static PersonRowMapper init() {
            return personRowMapper;
        }
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 判断当前id 有值否.
     *
     * @param id 人ID
     * @return 大于零 有值 否则没有值
     */
    public boolean readPersonCountById(long id) {
        String sql = "SELECT count(id) FROM person WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Integer.class) != 0;
    }

    /**
     * 根据 id 查找人信息.
     *
     * @param id 人ID
     * @return 返回人
     */
    public Person readPersonById(long id) {
        String sql = "SELECT * FROM person WHERE id= :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sql, parameterSource, PersonRowMapper.init());
    }

    /**
     * 根据人id 读取 人信息.
     *
     * @param id 人ID
     * @return Map人
     */
    public Map<String, Object> readMapPersonById(long id) {
        String sql = "SELECT * FROM person WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForMap(sql, parameterSource);
    }

    /**
     * 读取所有的人信息.
     *
     * @return 返回所有的人信息
     */
    public List<Person> readPerson() {
        String sql = "SELECT * FROM person";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return this.namedParameterJdbcTemplate.query(sql, parameterSource, PersonRowMapper.init());
    }

    /**
     * 读取人map对象list.
     *
     * @return 人map对象list.
     */
    public List<Map<String, Object>> readMapPerson() {
        String sql = "SELECT * FROM person";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return this.namedParameterJdbcTemplate.queryForList(sql, parameterSource);
    }

    /**
     * 增加 Person.
     *
     * @param person 人表
     */
    public void addPerson(Person person) {
        String sql = "INSERT INTO person (name,phone,address,email) VALUES(:name,:phone,:address,:email)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", person.getName());
        parameterSource.addValue("phone", person.getPhone());
        parameterSource.addValue("address", person.getAddress());
        parameterSource.addValue("email", person.getEmail());
        this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    /**
     * 删除人.
     *
     * @param id 人ID
     */
    public void removePerson(long id) {
        String sql = "DELETE FROM person WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    /**
     * 更新人名称.
     *
     * @param name 人名称
     * @param id   人ID
     */
    public void modifyName(String name, long id) {
        String sql = "UPDATE person SET name = :name WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", name);
        parameterSource.addValue("id", id);
        this.namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
