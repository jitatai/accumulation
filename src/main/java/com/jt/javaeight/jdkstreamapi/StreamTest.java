package com.jt.javaeight.jdkstreamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class StreamTest {

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }


    @Test
    public void testStreamApi() {
        List<String> collect = list.stream().filter(p -> p.age > 18).map(person -> person.name).sorted(String::compareTo).collect(Collectors.toList());
        System.out.println(collect);
        list.sort(Comparator.comparing(Person::getName));
    }

    public static void main(String[] args) {
        // 我们先学中间操作

        // 1.先获取流（不用管其他乱七八糟的创建方式，记住这一个就能应付95%的场景）
        Stream<Person> stream = list.stream();
        // 2.过滤年纪大于18岁的
        Stream<Person> filteredByAgeStream = stream.filter(person -> person.getAge() > 18);
        // 3.只要名字，不需要整个Person对象（为什么在这个案例中，filter只能用Lambda，map却可以用方法引用？）
        Stream<String> nameStream = filteredByAgeStream.map(Person::getName);
        // 4.现在返回值是Stream<String>，没法直接使用，帮我收集成List<String>
        List<String> nameList = nameStream.collect(Collectors.toList());

        list.sort(Comparator.comparingInt(Person::getAge));

        String result = list.stream().map(Person::getAddress).collect(Collectors.joining("~"));
        // 现在还对collect()为什么传递Collectors.toList()感到懵逼吗？

        // 去重
        list.stream().collect(
                Collectors.collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))), ArrayList::new)
        );

        Set<Object> seen = ConcurrentHashMap.newKeySet();
        List<Person> collect = list.stream().filter(p -> {
            boolean add = seen.add(p.getAddress());
            System.out.println(add);
            return add;
        }).collect(Collectors.toList());
        System.out.println(collect);

        collect = list.stream().filter(distinctByKey(Person::getAddress)).collect(Collectors.toList());
        System.out.println(collect);

        // collect 聚合
        list.stream().collect(Collectors.maxBy(Comparator.comparing(Person::getAge)));
        list.stream().max(Comparator.comparingInt(Person::getAge));

        // collect count
        list.stream().collect(Collectors.counting());
        list.stream().count();

        // 分组 group age
        list.stream().collect(Collectors.groupingBy(Person::getAge));

        // GROUP BY address, age
        list.stream().collect(Collectors.groupingBy(Person::getAddress,Collectors.groupingBy(Person::getAge)));

        // age > 18
        list.stream().collect(Collectors.partitioningBy(person -> person.age > 18));

        // 统计
        list.stream().collect(Collectors.averagingInt(Person::getAge));

        // 所有统计
        list.stream().collect(Collectors.summarizingInt(p ->p.getAge()));

        // flatMap 多个流 合成一个流 要求返回值是Stream
        //list.stream().flatMap(p-> Arrays.stream(p.getLikes())).collect(Collectors.toSet());

        // forEach
        list.forEach(System.out::println);
        list.stream().forEach(person -> System.out.println(person));
        System.out.println("------");
        // peek 设置值观察
        list.stream().peek(person -> {
            person.setAge(18);
            System.out.println(person);
        });
        Arrays.asList(1,3,45,5).stream().peek(v-> System.out.println("old:"+v))
                .filter(v->v>2).peek(v-> System.out.println("new:" + v)).forEach(System.out::println);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> {
            boolean add = seen.add(keyExtractor.apply(t));
            System.out.println(add);
            return add;
        };
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
        private String [] likes;

        public Person(String name, Integer age, String address, Double salary) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.salary = salary;
        }
    }
}