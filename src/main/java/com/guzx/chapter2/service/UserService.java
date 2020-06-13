package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public void printUser(User user){
        System.out.println(user.getUserName());
        System.out.println(user.getUid());
        System.out.println(user.getNote());
    }
}
