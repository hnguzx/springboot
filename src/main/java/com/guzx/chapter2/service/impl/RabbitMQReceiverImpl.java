package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.service.RabbitMQReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiverImpl implements RabbitMQReceiver {
    @Override
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiverMsg(String message) {
        System.out.println("收到消息：" + message);
    }

    @Override
    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiverObj(Object message) {
        System.out.println("收到对象消息：" + message);
    }
}
