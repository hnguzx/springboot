package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int insertUser(User user);
}
