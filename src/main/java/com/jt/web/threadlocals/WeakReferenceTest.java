package com.jt.web.threadlocals;

import java.lang.ref.WeakReference;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/25 19:46
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        Car car = new Car();
        WeakReference<Car> weakCar = new WeakReference<>(car);
        System.gc();
        System.out.println(weakCar.get());

        car = null;
        System.out.println(weakCar.get());

        System.gc();
        System.out.println(weakCar.get());

    }

    static class Car{

    }
}
