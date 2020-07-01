package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.User_MyBatis;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatisUserDao {
    public User_MyBatis getUser(Long id);
}
