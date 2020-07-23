package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.User;

import java.util.List;

public interface UserService {
    public User insertUser(User user);

    public User getUserById(Long id);

    public List<User> getUsers(String userName, String note, int start, int limit);

    public int updateUser(User user);

    public int updateUserName(Long id, String userName);

    public int deleteUser(Long id);
}
