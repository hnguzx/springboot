package com.guzx.chapter2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {

    private RedisConnectionFactory connectionFactory = null;

//    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(1000);
        // 创建jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        // 获取单机的redis配置
        RedisStandaloneConfiguration redisStandaloneConfiguration = connectionFactory.getStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }

//    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        // RedisTemplate会自动初始化StringSerializer
        RedisSerializer stringSerializer = template.getStringSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);

        template.setConnectionFactory(this.connectionFactory);
        return template;
    }


}
