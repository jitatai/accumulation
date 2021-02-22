package com.jt.javaeight.jdkstreamapi;

import java.util.*;
import java.util.stream.Collectors;

public class FlatMapTest {
    /**
     * 需求：
     * 1.要求返回所有的key，格式为 list<Long>      提示:keyset
     * 2.要求最终返回所有value，格式为 List<Long>   提示:flatMap()，Function需要啥你就转成啥
     *
     * @param args
     */

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }
    public static void main(String[] args) {
        Map<Long, List<Long>> map = new HashMap<>();
        map.put(1L, new ArrayList<>(Arrays.asList(1L, 2L, 3L)));
        map.put(2L, new ArrayList<>(Arrays.asList(4L, 5L, 6L)));

        ArrayList<Long> longs = new ArrayList<>(map.keySet());
        System.out.println(longs);

        List<Long> collect = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);

        // 要求分组统计出各个城市的年龄总和，返回格式为 Map<String, Integer>。
        Map<String, Double> collect1 = list.stream().collect(Collectors.groupingBy(Person::getAddress,Collectors.averagingDouble(Person::getAge)));
        System.out.println(collect1);

        execute(System.out::println);
    }

    static void execute(Runnable runnable){
        try {
            runnable.run();
        }catch (Exception e){
            new RuntimeException(e.getMessage());
        }
    }

}