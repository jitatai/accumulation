package com.jt.javaeight.functioninterfaces;

public class FunctionalInterfaceTest {

    public static void main(String[] args) {
		// Ambiguous method call
        lambdaMethod((Param2) () -> System.out.println("test"));
    }

    /**
     * 方法重载
     *
     * @param param1
     */
    public static void lambdaMethod(Param1 param1) {
        param1.print();
    }

    /**
     * 方法重载
     *
     * @param param2
     */
    public static void lambdaMethod(Param2 param2) {
        param2.print();
    }

    /**
     * 函数式接口1
     */
    interface Param1 {
        void print();
    }

    /**
     * 函数式接口2
     */
    interface Param2 {
        void print();
    }

}