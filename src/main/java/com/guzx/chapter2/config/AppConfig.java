package com.guzx.chapter2.config;

import com.guzx.chapter2.dao.MyBatisUserDao;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.util.Properties;

@Configuration
//@ComponentScan(basePackages = {"com.guzx.chapter2"},excludeFilters = {@Filter(classes = {Service.class})})
//@ComponentScan(basePackageClasses = {User.class})
//@ComponentScan(value = "com.guzx.chapter2",lazyInit = false)
@ComponentScan(value = "com.guzx.chapter2")
public class AppConfig {

    /*@Bean(name = "user")
    public User initUser(){
        User user = new User();
        user.setUid(1L);
        user.setUserName("test");
        user.setNote("testNote");
        return user;
    }*/

    @Bean(value = "dataSource")
    public DataSource getDataSource() {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://localhost:3306/springboot");
        properties.setProperty("username", "root");
        properties.setProperty("password", "970712");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public void init() {
        System.out.println("dataSource自定义初始化方法");
    }

    public void destroy() {
        System.out.println("dataSource自定义销毁方法");
    }

}
