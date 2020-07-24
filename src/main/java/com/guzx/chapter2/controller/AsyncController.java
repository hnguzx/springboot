package com.guzx.chapter2.controller;

import com.guzx.chapter2.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/page")
    public String asyncPage() {
        System.out.println("请求线程名称为：" + Thread.currentThread().getName());
        asyncService.generateReport();
        return "async/index";
    }
}
