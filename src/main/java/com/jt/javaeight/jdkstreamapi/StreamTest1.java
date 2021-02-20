package com.jt.javaeight.jdkstreamapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/20 17:35
 */
public class StreamTest1 {
    private static List<Person> list;
    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }
    public static void main(String[] args) {
        // 测试sorted
        list.stream().filter(person -> person.getAge() > 18).map(Person::getAddress)
                .sorted(Comparator.comparing(t -> t, Comparator.comparingInt(String::length)))
                .collect(Collectors.toList());
        list.stream().filter(person -> person.getAge() > 18).map(Person::getAddress)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        list.stream().filter(person -> person.getAge() > 20).
                sorted( Comparator.comparing(Person::getName,String::compareTo) .reversed() )
                .collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private Integer age;
    private String address;
    private Double salary;
}
