package com.guzx.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webSocket")
public class WebSocketContorller {

    @GetMapping("/view")
    public String webSocketPage() {
        return "webSocket/index";
    }
}
