package com.guzx.chapter2.controller;

import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.impl.JdbcTemplImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplImpl jdbcTempl;

    @RequestMapping("/add")
    @ResponseBody
    public int addUser(String user_name, int sex, String note) {
        User user = new User();
        user.setUserName(user_name);
        user.setNote(note);

        SexEnum sexEnum = SexEnum.getEnumById(sex);
        user.setSex(sexEnum);


        int result = jdbcTempl.insertUser(user);
        return result;
    }
}
