package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    int insertUser(User user);

    User getUserById(Long id);

    List<User> getUsers(String userName, String note, int start, int limit);

    int updateUser(User user);

    int updateUserName(Long id,String userName);

    int deleteUser(Long id);
}
