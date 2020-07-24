package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateReport() {
        System.out.println("异步线程池执行中，线程名为：" + Thread.currentThread().getName());
    }
}
