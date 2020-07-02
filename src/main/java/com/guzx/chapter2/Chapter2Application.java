package com.guzx.chapter2;


import com.guzx.chapter2.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.guzx.chapter2"})
@EnableJpaRepositories(basePackages = "com.guzx.chapter2.dao")
@EntityScan(basePackages = "com.guzx.chapter2.pojo")
@MapperScan(basePackages = {"com.guzx.chapter2.dao"})
//@RestController
//@ComponentScan(basePackages = {"com.guzx.chapter2"},excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})
public class Chapter2Application {

//    @RequestMapping("/")
//    String index(){
//        return "index";
//    }

    @Autowired
    SqlSessionFactory sqlsessionfactory = null;

    @Bean
    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao() {
        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlsessionfactory);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
