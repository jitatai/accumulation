package com.jt.web.threads;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "我是：" +this.getClass();
            }
        });
        System.out.println("启动任务");
        new Thread(futureTask).start();
        Object o = null;
        try {
            o = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("任务结束，结果为：%s",o));


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> "callable");
        String s = submit.get();
    }
}
