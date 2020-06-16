package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.User_t;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public void printUser(User_t userT){
        System.out.println(userT.getUserName());
        System.out.println(userT.getUid());
        System.out.println(userT.getNote());
    }
}
