package com.jt.spring.listener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.*;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/20 10:01
 */
@Configuration
public class AsyncThreadPoolEventConfig {

    @Bean("threadPool")
    public Executor getThreadPool(){
        return new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Bean(name="applicationEventMulticaster")
    public ApplicationEventMulticaster getApplicationEventMulticaster(){
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
