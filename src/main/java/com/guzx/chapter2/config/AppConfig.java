package com.guzx.chapter2.config;

import com.guzx.chapter2.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "user")
    public User initUser(){
        User user = new User();
        user.setUid(1L);
        user.setUserName("test");
        user.setNote("testNote");
        return user;
    }
}
