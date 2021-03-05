package com.jt.web.request.threads;

public class MyRunnble implements Runnable {

    public static void main(String[] args) {
        Thread t = new Thread();
        Thread tt = new Thread(t);
        Thread tt1 = new Thread(t);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable's run method is running");
            }
        }){
            @Override
            public void run() {
                System.out.println("Thread's run method is running");
            }
        }.start();
    }

    @Override
    public void run() {

    }
}
