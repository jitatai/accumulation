package com.jt.javaeight.streamapi;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/19 17:22
 */
public class MyMockStream {
    public static void main(String[] args) {
        MyList<Person> myList =new MyList<Person>();
        myList.add(new Person(18,"jiatai"));
        myList.add(new Person(18,"jiatai"));
        myList.add(new Person(18,"jiatai"));

    }

    static class MyList<E>{
        private List<E> list = new ArrayList<>();

        public void add(E e){
            list.add(e);
        }

        public List<E> filter(Predicate<E> filter){
            List filteredList = new ArrayList();
            for (E e : list) {
                if (filter.test(e)){
                    filteredList.add(e);
                }
            }
            return filteredList;
        }

        public <R> List<R> map(Function<E,R> mapper){
            List<R> mapperList = new ArrayList<>();
            for (E e : list) {
                mapperList.add(mapper.mapper(e));
            }
            return mapperList;
        }
    }
    interface Predicate<E>{
        boolean test(E e);
    }
    interface Function<E,R>{
        R mapper(E e);
    }
    @Setter
    @AllArgsConstructor
    static class Person{
        int age;
        String name;
    }
}

