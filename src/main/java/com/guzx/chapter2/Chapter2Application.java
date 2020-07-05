package com.guzx.chapter2;


import com.guzx.chapter2.dao.MyBatisUserDao;
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

    @PostConstruct
    public void viewTransactonManager(){
        System.out.println(transactionManager.getClass().getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
