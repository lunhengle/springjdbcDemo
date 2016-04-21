package com.lhl.service.impl;

import com.lhl.dao.IPersonDao;
import com.lhl.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenovo on 2016/4/19.
 * 人 service 实现类
 */
@Service
@Transactional
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonDao iPersonDao;

    /**
     * 修改名称.
     *
     * @param name 名称
     * @param id   id
     */
    @Transactional(propagation = Propagation.NEVER)
    public void modifyName(String name, long id) {
        iPersonDao.modifyName(name, id);
    }
}
