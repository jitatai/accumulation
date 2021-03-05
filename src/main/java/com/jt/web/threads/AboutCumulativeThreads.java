package com.jt.web.threads;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/5 10:02
 */
public class AboutCumulativeThreads {
    // static共享数据
//    static AtomicInteger sum = new AtomicInteger(0);
    static volatile int sum = 0;
    public static void main(String[] args) {
        Runnable target = () -> {
            int count = 10000;
            while (count > 0){
                // 造成线程安全问题在于 sum++非原子操作
                sum++;
                count--;
            }
        };
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();
        new Thread(target).start();


        dealWithException(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        });
    }

    static void dealWithException(Runnable runnable){
        try {
            runnable.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
