package com.guzx.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceporController {

    @GetMapping("/start")
    public String start(){
        System.out.println("执行处理器逻辑");
        return "/welcome";
    }
}
