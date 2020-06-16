package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.User;

import java.util.List;

public interface JdbcTemplUserService {

    public User getUser(Long id);

    public List<User> findUsers(String userName,String note);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Long id);
}
