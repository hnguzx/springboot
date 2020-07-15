package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.dao.MyBatisUserDao;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {

    @Autowired
    private MyBatisUserDao myBatisUserDao;

    @Override
    public User_MyBatis getUser(Long id) {
        return myBatisUserDao.getUser(id);
    }

    @Override
    public List<User_MyBatis> getUsers(String userName, String note) {
        return myBatisUserDao.getUsers(userName, note);
    }
}
