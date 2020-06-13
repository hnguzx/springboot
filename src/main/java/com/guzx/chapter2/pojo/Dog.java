package com.guzx.chapter2.pojo;

import com.guzx.chapter2.pojo.definition.Animal;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void user() {
        System.out.println("狗可以看门！");
        System.out.println(Dog.class.getSimpleName());
    }
}
