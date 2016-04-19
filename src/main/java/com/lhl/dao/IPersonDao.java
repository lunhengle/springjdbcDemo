package com.lhl.dao;

import com.lhl.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 * 人dao接口
 */
public interface IPersonDao {
    /**
     * 判断当前id 有值否.
     *
     * @param id 人ID
     * @return 大于零 有值 否则没有值
     */
    boolean readPersonCountById(long id);

    /**
     * 根据 id 查找人信息.
     *
     * @param id 人ID
     * @return 返回人
     */
    Person readPersonById(long id);

    /**
     * 根据人id 读取 人信息.
     *
     * @param id 人ID
     * @return Map人
     */
    Map<String, Object> readMapPersonById(long id);

    /**
     * 读取所有的人信息.
     *
     * @return 返回所有的人信息
     */
    List<Person> readPerson();


    /**
     * 读取人map对象list.
     *
     * @return 人map对象list.
     */
    List<Map<String, Object>> readMapPerson();

    /**
     * 增加 Person.
     *
     * @param person 人表
     */
    void addPerson(Person person);

    /**
     * 删除人.
     *
     * @param id 人ID
     */
    void removePerson(long id);

    /**
     * 更新人名称.
     *
     * @param name 人名称
     * @param id   人ID
     */
    void modifyName(String name, long id);


}
