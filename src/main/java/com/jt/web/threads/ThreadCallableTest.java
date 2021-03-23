package com.jt.web.threads;

import java.util.concurrent.*;

public class ThreadCallableTest {
    public static void main(String[] args) {
        // 方式一：重写run方法
        new Thread(){
            @Override
            public void run(){
                System.out.println("xxxx");
            }
        }.start();

        new Worker().begin();

        // 方式二：传入Runnable实例
        new Thread(()->{
            System.out.println("xxxx");
        }).start();

        // 方式三：通过线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> xxxx = executorService.submit(() -> {
            System.out.println("xxxx");
            TimeUnit.SECONDS.sleep(3);
            return "成功";
        });
        try {
            String result = xxxx.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

    static class Worker implements Runnable{
        @Override
        public void run() {
            System.out.println("开始工作了");
        }

        public void begin(){
            new Thread(this).start();
        }
    }
}
