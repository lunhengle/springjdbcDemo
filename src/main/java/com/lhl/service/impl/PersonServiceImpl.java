package com.lhl.service.impl;

import com.lhl.dao.IPersonDao;
import com.lhl.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2016/4/19.
 * 人 service 实现类
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonDao iPersonDao;
}
