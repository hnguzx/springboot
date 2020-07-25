package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.pojo.Message;
import com.guzx.chapter2.service.ActiveMQObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

//@Service
public class ActiveMQObjectServiceImpl implements ActiveMQObjectService {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String myDestination = "my-destination";


    @Override
    public void sendObject(Message message) {
        System.out.println("发送消息：" + message);
        jmsTemplate.convertAndSend(myDestination, message);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveObject(Message message) {
        System.out.println("收到消息：" + message);
    }
}
