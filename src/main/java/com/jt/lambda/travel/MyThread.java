package com.jt.lambda.travel;

public class MyThread {

    private MyRunnable target;
    public MyThread(){

    }
    public MyThread(MyRunnable target){
        this.target = target;
    }
    public void run() {
        System.out.println("去12306买了一张票");
        System.out.println("坐火车...");
    }

    public void start() {
        if (target ==null){
            run();
            return;
        }
        target.run();
    }
}