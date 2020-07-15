package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.User_MyBatis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyBatisUserDao {
    public User_MyBatis getUser(Long id);

    public List<User_MyBatis> getUsers(String userName, String note);
}
