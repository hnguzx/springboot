package com.guzx.chapter2.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@ServerEndpoint("/ws")
public class WebSocketServiceImpl {
    // 在线人数，应该要是线程安全的
    private static int onlineCount = 0;
    // concurrent包的线程安全set，用来存放每个客户端对应的WebSocketServerImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    // 与某个客户端建立的会话，通过它给客户端发送信息
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为：" + getOnlineCount());

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一个连接关闭了！当前在线人数为：" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用
     *
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("来自客户端的消息：" + message);
        for (WebSocketServiceImpl webSocketService : webSocketSet) {
            try {
                Object o = JSON.parse(message);
                webSocketService.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 发生错误时调用
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("出错了！");
        throwable.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServiceImpl.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServiceImpl.onlineCount--;
    }

}
