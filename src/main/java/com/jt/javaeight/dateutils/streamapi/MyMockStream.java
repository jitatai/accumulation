package com.jt.javaeight.dateutils.streamapi;

import lombok.AllArgsConstructor;
import lombok.Data;

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
        myList.add(new Person(20,"1jiatai"));
        myList.add(new Person(30,"2jiatai"));
        MyList<Person> filter = myList.filter(person -> person.getAge() > 18);
        System.out.println(filter.list);

        MyList<String> map = myList.map(Person::getName);
        System.out.println(map.list);
    }

    static class MyList<E>{
        private List<E> list = new ArrayList<>();

        public void add(E e){
            list.add(e);
        }

        public MyList<E> filter(Predicate<E> filter){
            MyList<E> filteredList = new MyList<>();
            for (E e : list) {
                if (filter.test(e)){
                    filteredList.add(e);
                }
            }
            return filteredList;
        }

        public <R> MyList<R> map(Function<E,R> mapper){
            MyList<R> mapperList = new MyList<>();
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
    @Data
    @AllArgsConstructor
    static class Person{
        int age;
        String name;
    }
}

