package com.guzx.chapter2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.guzx.chapter2"},excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class})})
//@ComponentScan(basePackageClasses = {User.class})
//@ComponentScan(value = "com.guzx.chapter2")
public class AppConfig {

    /*@Bean(name = "user")
    public User initUser(){
        User user = new User();
        user.setUid(1L);
        user.setUserName("test");
        user.setNote("testNote");
        return user;
    }*/
}
