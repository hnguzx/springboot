package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.User_MyBatis;

import java.util.List;

public interface MyBatisUserService {
    public User_MyBatis getUser(Long id);

    public List<User_MyBatis> getUsers(String userName, String note);
}
