package com.guzx.chapter2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 注册服务器端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册stomp端点，客户端在订阅或发布消息到目的地路径前，要连接该端点。
        registry.addEndpoint("/socket").withSockJS();
//        registry.addEndpoint("/wsuser").withSockJS();
    }

    /**
     * 点对点通信
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 客户端订阅路径前缀，消息代理
        // registry.enableSimpleBroker("/sub","/queue"); // 基于内存的简单代理
        // 所有目的地前缀为“/topic”或“/queue”的消息都会发送到STOMP代理中。
        registry.enableStompBrokerRelay("/sub", "/queue");

        // 服务端点请求前缀，客户端发往服务端的端点
        // 所有目的地以“/request”打头的消息都将会路由到带有@MessageMapping注解的方法中，而不会发布到代理队列或主题中。
        registry.setApplicationDestinationPrefixes("/request");
    }
}
