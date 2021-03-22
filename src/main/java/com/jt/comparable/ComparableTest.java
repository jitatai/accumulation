package com.jt.comparable;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/19 17:15
 */

public class ComparableTest {
    public static List<Person> list;
    static {
        list = new ArrayList<>();
        list.add(new Person(20,"888"));
        list.add(new Person(25,"555"));
        list.add(new Person(23,"666"));
    }
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("");
        strings.add("");

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        System.out.println(list);
    }
}
@Data
@AllArgsConstructor
class Person{
    int age;
    String name;
}
