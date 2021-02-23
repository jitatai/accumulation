package com.jt.javaeight.jdkstreamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamTestCollect {

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("iron", 17, "宁波", 888.8));
    }

    public static void main(String[] args) {
        // 最常用的4个方法

        // 把结果收集为List
        List<String> toList = list.stream().map(Person::getAddress).collect(Collectors.toList());
        System.out.println(toList);
        // 把结果收集为Set
        Set<String> toSet = list.stream().map(Person::getAddress).collect(Collectors.toSet());
        System.out.println(toSet);
        // 把结果收集为Map，前面的是key，后面的是value，如果你希望value是具体的某个字段，可以改为toMap(Person::getName, person -> person.getAge())
        Map<String, Person> nameToPersonMap = list.stream().collect(Collectors.toMap(Person::getName, person -> person,(first,last) -> first));
        System.out.println(nameToPersonMap);

        // 把结果收集起来，并用指定分隔符拼接
        String result = list.stream().map(p->p.address).collect(Collectors.joining("~"));
        System.out.println(result);
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
    }
}