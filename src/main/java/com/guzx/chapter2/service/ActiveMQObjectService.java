package com.guzx.chapter2.service;

import com.guzx.chapter2.pojo.Message;

public interface ActiveMQObjectService {
    /**
     * 发送消息
     * @param message
     */
    public void sendObject(Message message);

    /**
     * 接收消息
     * @param message
     */
    public void receiveObject(Message message);
}
