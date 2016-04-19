package com.lhl.service;

import com.lhl.AbstractBaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lenovo on 2016/4/19.
 * 人 测试
 */
public class PersonServiceTest extends AbstractBaseTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IPersonService iPersonService;


}
