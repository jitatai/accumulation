package com.jt.javaeight.functioninterfaces;

public class FunctionalInterfaceTest0 {

    /**
     * 给函数式接口赋值的格式：
     * 函数式接口 = 入参 -> 出参/制造出参的语句
     *
     * @param args
     */
    public static void main(String[] args) {
        FunctionalInterface1<String> interface1 = str -> System.out.println(str);
        FunctionalInterface2 interface2 = () -> {
            return "abc";
        };
        FunctionalInterface3<String,Integer> interface3 = str -> Integer.parseInt(str);
        FunctionalInterface4 interface4 = str -> str.length() > 3;
    }


    /**
     * 消费型，吃葡萄不吐葡萄皮：有入参，无返回值
     * (T t) -> {}
     */
    @FunctionalInterface
    interface FunctionalInterface1<T> {
        void method(T t);
    }

    /**
     * 供给型，无中生有：没有入参，却有返回值
     * () -> T t
     */
    @FunctionalInterface
    interface FunctionalInterface2 {
        String method();
    }

    /**
     * 映射型，转换器：把T转成R返回
     * T t -> R r
     */
    @FunctionalInterface
    interface FunctionalInterface3<E,R> {
        R method(E e);
    }
    /**
     * 特殊的映射型：把T转为boolean
     * T t -> boolean
     */
    @FunctionalInterface
    interface FunctionalInterface4 {
        boolean method(String str);
    }
}