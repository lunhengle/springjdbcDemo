package com.lhl.service.impl;

import com.lhl.dao.IUserDao;
import com.lhl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenovo on 2016/4/19.
 * 用户 service 接口实现类
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
}
