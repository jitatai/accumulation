package com.jt.javaeight.optional;

import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/23 10:13
 */
public class NullableWrapper<T> {
    /**
     * value的值需要定义成final 生命周期变长
     */
    private final T value;
    private final static NullableWrapper<?> EMPTY = new NullableWrapper<>();

    private NullableWrapper(){
        this.value = null;
    }

    /**
     * 构造器，包装指定的【非空值】
     * 如果传入null会抛NullPointerException
     * @param data
     */
    private NullableWrapper(T data){
        this.value = Objects.requireNonNull(data);
    }

    public static <T> NullableWrapper<T> ofNullable(T value) {
        return (NullableWrapper<T>) ifNullEsle(value,EMPTY, v->new NullableWrapper<>(value));
    }

    /**
     * 核心方法：
     * 1.如果value为null，直接返回空的Wrapper
     * 2.如果value不为null，则使用mapper对value进行处理，往下剥一层
     * （这是关键，一有机会就要往下剥一层，否则就是原地踏步）
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> NullableWrapper<R> map(Function<? super T,? extends R> mapper){
        Objects.requireNonNull(mapper);
        return ifNullEsle(value, ofNullable(null), v -> ofNullable(mapper.apply(value)));
    }

    /**
     * 终端操作 获取到value，给个备选值：other。当你确实是null时，返回other。
     */
    public T orElse(T other){
        return ifNullEsle(value,other,v->v);
    }

    public static <T,R> R ifNullEsle(T value,R other,Function<T,R> mapper){
        return value == null ? other : mapper.apply(value);
    }

    /**
     * 是否有值（非null）
     *
     * @return
     */
    public boolean isPresent() {
        return value != null;
    }


    // -------- 测试方法 ---------

    public static void main(String[] args) {
        // 全部不为null
        Son sonNotNull = new Son("大头儿子");
        Father fatherNotNull = new Father();
        fatherNotNull.setSon(sonNotNull);
        GrandPa grandPaNotNull = new GrandPa();
        grandPaNotNull.setFather(null);
        // 处理grandPa，观察map()中的处理方法有没有被调用
        String sonName1 = NullableWrapper.ofNullable(grandPaNotNull)
                .map(grandPa -> grandPa.getFather())
                .map(father -> father.getSon())
                .map(son -> son.getName())
                .orElse("没得到儿子的名字");

        // 全部为null
//        GrandPa grandPaNull = new GrandPa();
//        grandPaNull.setFather(null);
//        // 处理grandPa，观察map()，你会发现，从grandPa取出father后，由于发现是null，所以father->father.getSon()不会执行，避免了空指针
//        String sonName2 = NullableWrapper.ofNullable(grandPaNull)
//                .map(grandPa -> grandPa.getFather())
//                .map(father -> father.getSon())
//                .map(son -> son.getName())
//                .orElse("没得到儿子的名字");
    }

    // ---- 没啥实质内容，就是几个简单的类，我在getter方法中打印了一些信息 ----
    static class GrandPa {
        private Father father;

        public Father getFather() {
            System.out.println("GrandPa#getFather被调用了");
            return father;
        }

        public void setFather(Father father) {
            this.father = father;
        }
    }

    static class Father {
        private Son son;

        public Son getSon() {
            System.out.println("Father#getSon被调用了");
            return son;
        }

        public void setSon(Son son) {
            this.son = son;
        }
    }

    @AllArgsConstructor
    static class Son {
        private String name;

        public String getName() {
            System.out.println("Son#getName被调用了");
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
