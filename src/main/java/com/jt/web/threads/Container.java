package com.jt.web.threads;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @param <T>
 * @author jiatai
 */
public class Container<T> {
    private int count = 0;
    public static final int MAX_CONTAINER = 10;
    private LinkedList<T> list = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition comsumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public synchronized void put(T t) {
        try {
            while (list.size() == MAX_CONTAINER) {
                producer.await();
            }
            count++;
            list.add(t);
            comsumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T get() {
        try {
            while (list.size() == 0) {
                comsumer.await();
            }
            count--;
            producer.signalAll();
            return list.removeFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Container<String> container = new Container<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
        new Thread(() -> container.get()).start();
        new Thread(() -> container.put("")).start();
    }

}
