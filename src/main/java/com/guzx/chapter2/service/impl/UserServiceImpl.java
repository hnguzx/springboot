package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.dao.UserDao;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
    public User insertUser(User user) {
        if (userDao.insertUser(user) == 1) {
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUsers(String userName, String note, int start, int limit) {
        return userDao.getUsers(userName, note, start, limit);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updateUserName(Long id, String userName) {
        return userDao.updateUserName(id,userName);
    }

    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
