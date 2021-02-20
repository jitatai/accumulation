package com.jt.javaeight.methodref;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class MethodReferenceTest {

    private static final List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person(19));
        list.add(new Person(18));
        list.add(new Person(20));
    }

    public static void main(String[] args) {
        System.out.println(list);
        // sort()方法是List本身就有的，主要用来排序
        list.sort(Person::compare);
        System.out.println(list);
    }


    @Data
    @AllArgsConstructor
    static class Person {
        private Integer age;

        int compare(Person p){
            return this.age - p.age;
        }
    }

}