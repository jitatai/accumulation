package com.jt.web.threads;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/5 14:35
 */
public class MultiThreadedCase {
    synchronized void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1");
    }
    void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m2");
    }

    public static void main(String[] args) {
        MultiThreadedCase t = new MultiThreadedCase();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
