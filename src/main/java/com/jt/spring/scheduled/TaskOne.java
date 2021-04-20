package com.jt.spring.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/16 10:09
 */
@Slf4j
@Component
@EnableScheduling
public class TaskOne {

    @Async("taskExecutor")
    @Scheduled(cron = "*/10 * * * * ?")
    public void taskOne(){
        log.info("taskOne Begin...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("taskOne End...");
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void taskTwo(){
        log.info("taskTwo Begin...");
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("taskTwo End...");
    }
}
