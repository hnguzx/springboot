package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.Message;
import com.guzx.chapter2.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rabbitMQ")
public class RabbitMQController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @ResponseBody
    @GetMapping("/msg")
    public Map<String, Object> msg(String message) {
        rabbitMQService.sendMsg(message);
        return result("message", message);
    }

    @ResponseBody
    @GetMapping("/obj")
    public Map<String, Object> obj(Long id, String userName, String note) {
        Message message = new Message(id, userName, note);
        rabbitMQService.sendObject(message);
        return result("user", message);

    }


    public Map<String, Object> result(String key, Object object) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put(key, object);
        return result;
    }
}
