package com.guzx.chapter2;


import com.guzx.chapter2.dao.MyBatisUserDao;
import com.guzx.chapter2.listener.RedisMessageListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.guzx.chapter2.*"})
//@EnableJpaRepositories(basePackages = "com.guzx.chapter2.dao")
//@EntityScan(basePackages = "com.guzx.chapter2.pojo")
//方式三
@MapperScan(
        basePackages = "com.guzx.chapter2.*",
        // sqlSessionFactoryRef优先级低于sqlSessionTemplateRef
//        sqlSessionFactoryRef = "sqlSessionFactory",
//        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)
//@RestController
//@ComponentScan(basePackages = {"com.guzx.chapter2"},excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class Chapter2Application {

//    @RequestMapping("/")
//    String index(){
//        return "index";
//    }

    // 方式一
    /*@Autowired
    SqlSessionFactory sqlsessionfactory = null;

    @Bean
    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlsessionfactory);
        return bean;
    }*/

    // 方式二
    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //定义扫描器类
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //加载sqlSessionFactory
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //定义扫描的包
        mapperScannerConfigurer.setBasePackage("com.guzx.chapter2.*");
        //限定要扫描的类
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }*/

    @Autowired
    PlatformTransactionManager transactionManager = null;

//    @PostConstruct
//    public void viewTransactonManager(){
//        System.out.println(transactionManager.getClass().getName());
//    }

    // redis配置
    @Autowired
    private RedisTemplate redisTemplate = null;

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }

    // Redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    // Redis消息监听器
    @Autowired
    private MessageListener messageListener = null;

    // 任务池
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler = null;

    /**
     * 创建任务池，运行线程等待处理redis的消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(threadPoolTaskScheduler!=null){
            return this.threadPoolTaskScheduler;
        }
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }

    /**
     * 定义Redis的监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer  container = new RedisMessageListenerContainer();
        // redis连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道，名称为topic1
        Topic topic = new ChannelTopic("topic1");
        // 使用监听器监听redis的消息
        container.addMessageListener(messageListener,topic);
        return container;
    }



    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
