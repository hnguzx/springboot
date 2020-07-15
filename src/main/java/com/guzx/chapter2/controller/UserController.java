package com.guzx.chapter2.controller;

//import com.guzx.chapter2.dao.JpaUserRepository;

import com.alibaba.fastjson.JSONArray;
import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.pojo.User_JPA;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.impl.JdbcTemplImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/detail")
    public ModelAndView userDetail(int id) {
        User user = jdbcTempl.getUser2(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/detail");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @RequestMapping("/detailJson")
    public ModelAndView userDetail2(int id) {
        User user = jdbcTempl.getUser2(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/table")
    public ModelAndView getAllUser() {
//        List<User> users = jdbcTempl.findUsers(null, null);
//        List<User> users = jdbcTempl.findAll();
        List<User_MyBatis> users = myBatisUserService.getUsers(null, null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/table");
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<User_MyBatis> getList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "note", required = false) String note) {
        List<User_MyBatis> userList = myBatisUserService.getUsers(userName, note);
        return userList;
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
