package com.guzx.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
