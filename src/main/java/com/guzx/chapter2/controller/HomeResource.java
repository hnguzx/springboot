package com.guzx.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return ("<h1>Welecom</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welecom User</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welecom Admin</h1>");
    }
}
