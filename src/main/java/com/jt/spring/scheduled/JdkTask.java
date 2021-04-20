package com.jt.spring.scheduled;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/19 15:01
 */
public class JdkTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("nihao shijie");
            }
        },1,5000);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(()-> System.out.println("我是大帅哥"),5,5, TimeUnit.SECONDS);
    }
}
