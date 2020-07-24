package com.guzx.chapter2.service;

public interface RabbitMQService {

    public void sendMsg(String message);

    public void sendObject(Object message);
}
