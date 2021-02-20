package com.jt.javaeight.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/18 14:33
 */
public class MyList<T> {
    private List<T> list = new ArrayList<>();

    public List<T> filter(Predicate<T> predicate){
        List<T> filterList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)){
                filterList.add(t);
            }
        }
        return filterList;
    }

    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.filter(v -> v > 2);
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.add(123);
        integerLinkedList.add(456);
        integerLinkedList.remove();
        System.out.println(integerLinkedList);

        long start = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(1);
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(time);
        long start1 = System.currentTimeMillis();
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println(time1);
}
}
