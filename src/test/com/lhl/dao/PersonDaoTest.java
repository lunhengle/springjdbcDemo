package com.lhl.dao;

import com.lhl.AbstractBaseTest;
import com.lhl.entity.Person;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/19.
 */
public class PersonDaoTest extends AbstractBaseTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IPersonDao iPersonDao;

    /**
     * 增加 人 测试.
     */
    @Test
    public void testAddPerson() {
        Person person = new Person();
        person.setName("伦恒乐");
        person.setPhone("15000000000");
        person.setAddress("北京市朝阳区");
        person.setEmail("944444444@outlook.com");
        iPersonDao.addPerson(person);
    }


    /**
     * 修改人.
     */
    @Test
    public void testModifyPerson() {
        iPersonDao.modifyName("伦恒乐1", 1);
    }

    /**
     * 根据id 查人
     */
    @Test
    public void testReadPersonById() {
        boolean b = iPersonDao.readPersonCountById(1);
        if (b) {
            Person person = iPersonDao.readPersonById(1);
            logger.info(">>>>>>> person.name= " + person.getName() + ">>>>>>>>>>person.phone=" + person.getPhone() + ">>>>>>>>>>>> person.address = " + person.getAddress() + ">>>>>>>> person.email = " + person.getEmail());
        }
    }

    /**
     * 查人列表.
     */
    @Test
    public void testReadPerson() {
        List<Person> personList = iPersonDao.readPerson();
        for (Person person : personList) {
            logger.info(">>>>>>> person.name= " + person.getName() + ">>>>>>>>>>person.phone=" + person.getPhone() + ">>>>>>>>>>>> person.address = " + person.getAddress() + ">>>>>>>> person.email = " + person.getEmail());
        }
    }

    /**
     * 根据 id 查人.
     */
    @Test
    public void testReadMapPersonById() {
        boolean b = iPersonDao.readPersonCountById(1);
        if (b) {
            Map<String, Object> map = iPersonDao.readMapPersonById(1);
            logger.info(">>>>>>> person.name= " + map.get("name") + ">>>>>>>>>> person.phone=" + map.get("phone") + ">>>>>>>>>>>> person.address = " + map.get("address") + ">>>>>>>> person.email = " + map.get("email"));
        }
    }

    /**
     * 查人 map.
     */
    @Test
    public void testReadListPerson() {
        List<Map<String, Object>> list = iPersonDao.readMapPerson();
        for (Map<String, Object> map : list) {
            logger.info(">>>>>>> person.name= " + map.get("name") + ">>>>>>>>>>person.phone=" + map.get("phone") + ">>>>>>>>>>>> person.address = " + map.get("address") + ">>>>>>>> person.email = " + map.get("email"));
        }
    }

    /**
     * 删除人.
     */
    @Test
    public void testRemovePerson() {
        iPersonDao.removePerson(1);
    }
}
