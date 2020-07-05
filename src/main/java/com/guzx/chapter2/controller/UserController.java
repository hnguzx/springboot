package com.guzx.chapter2.controller;

//import com.guzx.chapter2.dao.JpaUserRepository;
import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.pojo.User_JPA;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.impl.JdbcTemplImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplImpl jdbcTempl;

//    @Autowired
//    private JpaUserRepository jpaUserRepository;

    @Autowired
    private MyBatisUserService myBatisUserService;

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

    @RequestMapping("/getUser")
    @ResponseBody
    public User addUser(int id) {
        User user = jdbcTempl.getUser2(id);
        return user;
    }

    /*@RequestMapping("/jpa")
    @ResponseBody
    public User_JPA getUser(Long id) {
        User_JPA user = jpaUserRepository.findById(id).get();
        return user;
    }*/

    /*@RequestMapping("/jpaFindUser")
    @ResponseBody
    public List<User_JPA> getUser2(String userName,String note) {
        List<User_JPA> user = jpaUserRepository.findUser_JPAs(userName,note);
        return user;
    }*/

    /*@RequestMapping("/getUser_JPAById")
    @ResponseBody
    public User_JPA getUser3(Long id) {
        User_JPA user = jpaUserRepository.getUser_JPAById(id);
        return user;
    }*/

    @RequestMapping("/getUser_mybatis")
    @ResponseBody
    public User_MyBatis getUser4(Long id) {
        User_MyBatis user = myBatisUserService.getUser(id);
        return user;
    }


}
