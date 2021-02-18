package com.jt.lambda.strings;

import java.util.Comparator;

public class Demo {
    public static void main(String[] args) {
        compareString("123", "456", new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        compareString("123", "456", (o1, o2) -> {return o1.length() - o2.length();});
        compareString("123", "456", (o1, o2) ->  o1.length() - o2.length());
        compareString("123", "456", (o1, o2) ->  Integer.compare(o1.length(),o2.length()));
        compareString("123", "456", Comparator.comparingInt(String::length));
    }
    public static int compareString(String str1, String str2, Comparator<String> comparator){
        return comparator.compare(str1,str2);
    }
}
