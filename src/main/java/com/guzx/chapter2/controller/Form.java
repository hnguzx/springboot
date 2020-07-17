package com.guzx.chapter2.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@RequestMapping("/form")
public class Form {

    @RequestMapping("/format")
    public String showForm(){
        return "/format/formatter";
    }

    @PostMapping("/commit")
    @ResponseBody
    public Map<String,Object> format(@DataTimeForm(iso= ISO.DATE)Date date, @NumberFormat(pattern = "#,###.##") Double number){

    }
}
