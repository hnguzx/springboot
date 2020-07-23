package com.guzx.chapter2.controller;

//import com.guzx.chapter2.dao.JpaUserRepository;

import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.exception.NotFoundException;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.UserService;
import com.guzx.chapter2.service.impl.JdbcTemplImpl;
import com.guzx.chapter2.vo.ResultVo;
import com.guzx.chapter2.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

@RestController
public class UserController2 {

    @Autowired
    private UserService userService;

    private User changeToPo(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setNote(userVo.getNote());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        return user;
    }

    private UserVo changeToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setNote(user.getNote());
        userVo.setSexCode(user.getSex().getId());
        userVo.setSexName(user.getSex().getName());
        return userVo;
    }

    @GetMapping("/restful2")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("restful/index");
        return modelAndView;
    }

    @GetMapping("/user2/{id}")
    public UserVo getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserVo userVo = this.changeToVo(user);
        return userVo;
    }

    @PostMapping("/user2/entity")
    public ResponseEntity<UserVo> insertUser(@RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = this.changeToVo(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        String success = (result == null || result.getId() == null) ? "false" : "true";
        // 设置响应头
        httpHeaders.add("success", success);
        // 返回创建成功的状态码
        return new ResponseEntity<UserVo>(result, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/user2/annotation")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo insertUserAnnotation(@RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = this.changeToVo(user);
        return result;
    }
}
