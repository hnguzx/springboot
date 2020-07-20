package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.pojo.User_MyBatis;
import com.guzx.chapter2.service.MyBatisUserService;
import com.guzx.chapter2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/model")
@Controller
public class DataModel {

    @Autowired
    private MyBatisUserService userService;

    @GetMapping("/model")
    public String userModel(Long id, Model model) {
        User_MyBatis user = userService.getUser(id);
        model.addAttribute("user", user);
        return "data/user";
    }

    @GetMapping("/modelMap")
    public ModelAndView useModelMap(Long id, ModelMap modelMap) {
        User_MyBatis user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("data/user");
        modelMap.put("user", user);
        return modelAndView;
    }

    @GetMapping("/modelAndView")
    public ModelAndView useModelAndView(Long id, ModelAndView modelAndView) {
        User_MyBatis user = userService.getUser(id);
        modelAndView.setViewName("data/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
