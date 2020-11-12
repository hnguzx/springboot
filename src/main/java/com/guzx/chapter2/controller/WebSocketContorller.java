package com.guzx.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/webSocket")
public class WebSocketContorller {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/view")
    public String webSocketPage() {
        return "webSocket/index";
    }

    @GetMapping("/send")
    public String send() {
        return "webSocket/send";
    }

    @GetMapping("/receive")
    public String receive() {
        return "webSocket/receive";
    }

    @GetMapping("/sendUser")
    public String sendUser() {
        return "webSocket/sendUser";
    }

    @GetMapping("/receiveUser")
    public String receiveUser() {
        return "webSocket/receiveUser";
    }

    // 定义消息请求路径
    @MessageMapping("/send")
    // 定义结果发送到特定路径
    @SendTo("/sub/chat")
    public String sendMessage(String value) {
        return value;
    }

    /**
     * 发送消息给特定用户
     * @param principal
     * @param body
     */
    @MessageMapping("/sendUser")
    public void sendToUser(Principal principal, String body) {
        String srcUser = principal.getName();

        String[] args = body.split(",");
        String desUser = args[0];
        String message = "[" + srcUser + "]给你发来消息" + args[1];
        //  发送到用户和监听地址
        messagingTemplate.convertAndSendToUser(desUser, "/queue/customer", message);
    }
}
