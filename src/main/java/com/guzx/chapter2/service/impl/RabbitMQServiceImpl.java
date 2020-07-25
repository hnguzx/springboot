package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.service.RabbitMQService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
public class RabbitMQServiceImpl implements RabbitMQService, RabbitTemplate.ConfirmCallback {

    @Value("${rabbitmq.queue.msg}")
    private String msgRouting = null;
    @Value("${rabbitmq.queue.user}")
    private String userRouting = null;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送字符串消息：" + message);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgRouting, message);
    }

    @Override
    public void sendObject(Object message) {
        System.out.println("发送对象消息：" + message);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(userRouting, message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息消费成功！");
        } else {
            System.out.println("消息消费失败：" + s);
        }
    }
}
