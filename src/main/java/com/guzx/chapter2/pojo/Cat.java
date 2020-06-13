package com.guzx.chapter2.pojo;

import com.guzx.chapter2.pojo.definition.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Cat implements Animal {
    @Override
    public void user() {
        System.out.println("猫可以抓老鼠");
        System.out.println(Cat.class.getSimpleName());
    }
}
