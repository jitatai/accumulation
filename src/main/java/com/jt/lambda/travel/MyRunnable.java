package com.jt.lambda.travel;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * @author jiatai
 * 函数式接口：有且只有一个抽象方法
 * 闭包：定义在一个函数内部的函数
 * 内部的方法可以访问外部的局部变量。
 * 为什么java8以前传递给内部类的参数必须声明为final呢？
 * 局部变量在方法中，方法调用完毕就会弹栈，局部变量就会从内部消失，而匿名内部类的实例是在堆中的
 * 在未来的某个时刻被垃圾回收。两个的生命周期不同，一个实例不能持有不存在的变量引用。
 * 由于对象的生命周期无法改变，所以只能是局部变量做出让步：加final变为常量，常驻内存。
 * 这样，变量的生命周期反而可能比实例更长久。
 * java8以后是怎么处理的呢？不写final也不报错
 * 通过反编译会发现匿名内部类中构造方法中传递了final修饰的形参。
 * 匿名内部类为了让我们能使用外部的变量，底层生成了对应的final字段来接受他们（包括外部类的this）。
 *
 */
@FunctionalInterface
public interface MyRunnable {
    void run();
    default void running(){}
    //void test();
}
