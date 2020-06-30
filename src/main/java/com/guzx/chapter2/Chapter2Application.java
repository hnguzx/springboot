package com.guzx.chapter2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.guzx.chapter2"})
@EnableJpaRepositories(basePackages = "com.guzx.chapter2.dao")
@EntityScan(basePackages = "com.guzx.chapter2.pojo")
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
