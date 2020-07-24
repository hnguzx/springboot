package com.guzx.chapter2.controller;

import com.guzx.chapter2.pojo.Message;
import com.guzx.chapter2.service.ActiveMQObjectService;
import com.guzx.chapter2.service.ActiveMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/activeMQ")
public class ActiveMQController {
    @Autowired
    private ActiveMQObjectService objectService;
    @Autowired
    private ActiveMQService service;

    @GetMapping("/msg")
    @ResponseBody
    public Map<String, Object> msg(String message) {
        service.sendMsg(message);
        return result(true, message);
    }

    @RequestMapping("/object")
    @ResponseBody
    private Map<String, Object> objectMsg(Long id, String userName, String note) {
        Message message = new Message(id, userName, note);
        objectService.sendObject(message);
        return result(true, message);
    }

    private Map<String, Object> result(boolean success, Object message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}
