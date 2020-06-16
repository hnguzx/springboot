package com.guzx.chapter2.pojo;

import com.guzx.chapter2.pojo.definition.Animal;
import com.guzx.chapter2.pojo.definition.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.annotation.Annotation;

@Component
public class BussinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, PostConstruct, InitializingBean, PreDestroy, DisposableBean {

//    public BussinessPerson(@Autowired @Qualifier("dog") Animal animal){
//        this.animal = animal;
//    }

//    @Autowired(required = false)
//    @Qualifier("dog")
    private Animal animal = null;
//    private Animal dog = null;

    @Override
    public void animalService() {
        this.animal.user();
//        this.dog.user();
    }

    @Override
    @Autowired
    @Qualifier("dog")
    public void setAnimal(Animal animal) {
        this.animal = animal;
//        System.out.println("依赖延迟注入");
//        this.dog = animal;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        System.out.println("第二步，setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
//        System.out.println("第一步，setBeanName");
    }

    @Override
    public void destroy() throws Exception {
//        System.out.println("第九步，destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("第六步，afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("第三步，setApplicationContext");
    }

    @PostConstruct
    public void init(){
//        System.out.println("第五步，自定义初始化方法");
    }

    @PreDestroy
    public void customDestroy(){
//        System.out.println("第八步，自定义销毁方法");
    }
}
