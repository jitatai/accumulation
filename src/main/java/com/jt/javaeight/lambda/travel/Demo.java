package com.jt.javaeight.lambda.travel;

public class Demo {
    public static void main(String[] args) {
        // new一个MyThread对象，调用start()
        new MyThread().start();
        new MyThread(new ByTrain()).start();
        new MyThread(new ByAir()).start();
        // 匿名内部类优化
        new MyThread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println("去网上购票");
                System.out.println("做船去");
            }
        }).start();
        // lambda表达式优化
        new MyThread(() -> {
            System.out.println("去网上购票");
            System.out.println("做船去");
        }).start();
    }

}