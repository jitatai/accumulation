package com.jt.web.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "开始干活了");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "干完活了");
            });
            thread.start();
            threadList.add(thread);
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("所有线程睡三秒后我给你们恢复");
        /**
         * 唤醒所有行程
         */
        threadList.forEach((thread)->{
            LockSupport.unpark(thread);
        });
        TimeUnit.SECONDS.sleep(2);
    }
}
