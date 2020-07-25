package com.guzx.chapter2;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
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
@EnableCaching
//@EnableJms
@EnableRabbit
@EnableScheduling
public class Chapter2Application implements WebMvcConfigurer {

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
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (threadPoolTaskScheduler != null) {
            return this.threadPoolTaskScheduler;
        }
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }

    /**
     * 定义Redis的监听容器
     *
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // redis连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道，名称为topic1
        Topic topic = new ChannelTopic("topic1");
        // 使用监听器监听redis的消息
        container.addMessageListener(messageListener, topic);
        return container;
    }

    private LocaleChangeInterceptor localeChangeInterceptor = null;

    // 国际化解析器，name必须为localeResolver
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleChangeInterceptor() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        // 设置默认国际化区域
        sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return sessionLocaleResolver;
    }

    // 创建国际化拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        if (localeChangeInterceptor != null) {
            return localeChangeInterceptor;
        }
        localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*InterceptorRegistration registration = registry.addInterceptor(new CustomInterceptor());
        registration.addPathPatterns("/interceptor/*");

        InterceptorRegistration registration2 = registry.addInterceptor(new CustomInterceptor2());
        registration2.addPathPatterns("/interceptor/*");

        InterceptorRegistration registration3 = registry.addInterceptor(new CustomInterceptor3());
        registration3.addPathPatterns("/interceptor/*");*/

        registry.addInterceptor(localeChangeInterceptor());
    }

    /*@Value("${rabbitmq.queue.msg}")
    private String msgQueueName = null;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName = null;

    @Bean
    public Queue createQueueMsg(){
        return new Queue(msgQueueName,true);
    }

    @Bean
    public Queue createQueueUser(){
        return new Queue(userQueueName,true);
    }*/


    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
