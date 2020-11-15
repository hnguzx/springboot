package com.guzx.chapter2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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


    // 处理stomp消息
    // 定义消息请求路径
    // @MessageMapping标注的方法如果有返回值则会将值返回给消息代理
    @MessageMapping("/send")
    // 定义结果发送到特定路径，经过代理
    @SendTo("/sub/chat")
//    @SendTo("/subscribe")
    public String sendMessage(Principal principal, String value) {
        return value;
    }

    // 处理stomp消息
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

    // 请求-回应
    // 客户端订阅某一个目的地，然后预期在这个目的地上获得一个一次性的响应。
    // 异步处理，不经过代理
    @SubscribeMapping("/subscribe")
    public String handleSubscription(){
        System.out.println("hello sub");
        return "SubscribeMapping";
    }
}
