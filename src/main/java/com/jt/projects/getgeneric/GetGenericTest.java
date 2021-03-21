package com.jt.projects.getgeneric;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GetGenericTest {
    public static void main(String[] args) throws NoSuchMethodException {
        List<Person> list = null;
        testGeneric(list);
    }

    public static <T> void testGeneric(List<T> list) throws NoSuchMethodException {
        Class<GetGenericTest> testClass = GetGenericTest.class;
        Method method = testClass.getMethod("testGeneric", List.class);
        Type[] types = method.getGenericParameterTypes();
        ParameterizedType t = (ParameterizedType)types[0];
        //获得泛型class java.util.T
        System.out.println(t.getActualTypeArguments()[0].getTypeName());
        System.out.println(t.getRawType());
    }
}

class Person{

}

class Word{

}