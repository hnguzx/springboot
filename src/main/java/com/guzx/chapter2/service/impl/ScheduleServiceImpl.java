package com.guzx.chapter2.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class ScheduleServiceImpl {

    int count1 = 1;
    int count2 = 1;


    @Async
    @Scheduled(fixedRate = 1000)
    public void task1() {
        System.out.println("task1每隔一秒钟执行一次，现在是第：" + count1 + "次");
        count1++;
    }

    @Async
    @Scheduled(fixedRate = 2000)
    public void task2() {
        System.out.println("task2每隔两秒钟执行一次，现在是第：" + count2 + "次");
        count2++;
    }
}
