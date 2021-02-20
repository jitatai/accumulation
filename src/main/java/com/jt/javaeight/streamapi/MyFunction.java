package com.jt.javaeight.streamapi;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/19 17:08
 * E(enter) 入参类型
 * R(return) 返回值类型
 */
@FunctionalInterface
public interface MyFunction<E,R> {
    /**
     * 接受E类型 返回 R类型，也就是把E映射成R
     * @param e
     * @return
     */
    R apply(E e);
//    E apply2(R r);
}
