package com.guzx.chapter2.service;

public interface RabbitMQReceiver {
    public void receiverMsg(String message);

    public void receiverObj(Object message);

}
