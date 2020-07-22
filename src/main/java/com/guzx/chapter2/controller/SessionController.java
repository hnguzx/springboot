package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/session")
// 指定数据模型名称或者属性类型，保存到session中，在控制器执行完成后存入session
@SessionAttributes(names = {"user"},types = Long.class)
public class SessionController {

    @Autowired
    private MyBatisUserService userService;

    @GetMapping("/test")
    // @SessionAttribute("id")从session中取出指定数据填充参数
    public String test(@SessionAttribute("id") Long id, Model model){
        model.addAttribute("id_new",id);
        User_MyBatis user_myBatis = userService.getUser(id);
        model.addAttribute("user",user_myBatis);
        return "/session/test";
    }
}
