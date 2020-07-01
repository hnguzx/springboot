package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.dao.MyBatisUserDao;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {

//    @Autowired
//    private MyBatisUserDao userDao;

    @Override
    public User_MyBatis getUser(Long id) {
    return null;
//        return userDao.getUser(id);
    }
}
