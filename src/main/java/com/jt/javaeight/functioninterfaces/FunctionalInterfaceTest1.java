package com.jt.javaeight.functioninterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FunctionalInterfaceTest1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        /**
         * Predicate：特殊映射，只断是非   Function：T t --> f(x) --> boolean
         * Function：映射               Function：T t --> f(x) --> R r
         */
        list.stream().filter(v -> v > 2).map(Object::toString).collect(Collectors.toList());

        // Supplier，无源之水(S)，却滔滔不绝，没有入参，却可以get()
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> "supplier");

        // Consumer，黑洞，吃(C)葡萄不吐葡萄皮，入参拿过来就是一顿操作，不返回
        completableFuture.thenAccept(str -> System.out.println(str));

    }

}