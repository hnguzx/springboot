package com.guzx.chapter2.controller;

import com.guzx.chapter2.config.AppConfig;
import com.guzx.chapter2.pojo.User_t;
import com.guzx.chapter2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Index {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping("/index")
    public String index() {
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User_t u = (User_t) applicationContext.getBean("user");
        logger.info(u.getUserName());
        UserService service = (UserService) applicationContext.getBean("userService");
        service.printUser(u);*/
        return "index";
    }

    @RequestMapping("/noAnnotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String strVal) {
        Map<String, Object> result = new HashMap<>();
        result.put("intVal", intVal);
        result.put("longVal", longVal);
        result.put("strVal", strVal);
        return result;
    }

    @RequestMapping("/annotation")
    @ResponseBody
    // RequestParam value对应http参数
    public Map<String, Object> annotation(@RequestParam(value = "int_val",required = false) Integer intVal, @RequestParam("long_val") Long longVal, @RequestParam("str_val") String strVal) {
        Map<String, Object> result = new HashMap<>();
        result.put("int_Val", intVal);
        result.put("long_Val", longVal);
        result.put("str_Val", strVal);
        return result;
    }
}
