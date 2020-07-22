package com.guzx.chapter2.controller;

import org.apache.http.client.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @GetMapping("/test")
    public String test(Date date, ModelMap modelMap){
        System.out.println(modelMap.get("project_name"));
        System.out.println(DateUtils.formatDate(date,"yyyy-MM-dd"));
        throw new RuntimeException("抛出异常！");
    }
}
