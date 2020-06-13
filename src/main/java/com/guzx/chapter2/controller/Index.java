package com.guzx.chapter2.controller;

import com.guzx.chapter2.config.AppConfig;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @RequestMapping("/index")
    public String index(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User u = (User) applicationContext.getBean("user");
        logger.info(u.getUserName());
        UserService service = (UserService) applicationContext.getBean("userService");
        service.printUser(u);
        return "index";
    }
}
