package com.jt.web.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jiatai
 * @param <T>
 */
public class MyContainer<T> {
    private int count = 0;
    public static final int MAX_CONTAINER = 10;
    private LinkedList<T> list = new LinkedList<>();
    public synchronized void put(T t){
        while (list.size() == MAX_CONTAINER){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        list.add(t);
        this.notifyAll();
    }
    public synchronized T get(){
        while (list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        this.notifyAll();
        return list.removeFirst();
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        MyContainer<String> container = new MyContainer<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            },"c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            },"p"+i).start();
        }
        new Thread(()->container.get()).start();
        new Thread(()->container.put("")).start();
        List list = null;
        list.stream().map((v)->{
            return 1;
        });
    }

}
