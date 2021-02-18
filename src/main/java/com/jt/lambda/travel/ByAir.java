package com.jt.lambda.travel;

public class ByAir implements MyRunnable {
    @Override
    public void run() {
        System.out.println("去飞机站买票");
        System.out.println("做飞机去");
    }
}
