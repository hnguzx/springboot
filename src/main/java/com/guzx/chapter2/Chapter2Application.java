package com.guzx.chapter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@ComponentScan(basePackages = {"com.guzx.chapter2"},excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class Chapter2Application {

    @RequestMapping("/")
    String index(){
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
