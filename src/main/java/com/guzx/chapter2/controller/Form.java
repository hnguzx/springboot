package com.guzx.chapter2.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/form")
public class Form {

    @RequestMapping("/format")
    public String showForm() {
        return "/format/formatter";
    }

    @PostMapping("/commit")
    @ResponseBody
//    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @NumberFormat(pattern = "#,###.##") Double number) {
    public Map<String, Object> format(Date date, @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", date);
        dataMap.put("number", number);
        return dataMap;
    }
}
