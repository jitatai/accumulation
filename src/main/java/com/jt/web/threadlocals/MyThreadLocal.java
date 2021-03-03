package com.jt.web.threadlocals;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/3 15:44
 * 装饰器模式 将ThreadLocal作为成员变量进行包装。
 */
public class MyThreadLocal<T> {
    /**
     * 枚举获取单例ThreadLocal
     */
    private ThreadLocal<T> threadLocal = getThreadLocal();
    private enum ThreadLocalEnum{
        THREAD_LOCAL_ENUM;
        private ThreadLocal threadLocal;
        ThreadLocalEnum(){
            threadLocal = new ThreadLocal();
        }
    }
    public static ThreadLocal getThreadLocal() {
        return ThreadLocalEnum.THREAD_LOCAL_ENUM.threadLocal;
    }

    public static <T>  T get(){
        return (T) getThreadLocal().get();
    }

    public static void put(String key){
        getThreadLocal().set(key);
    }

    public static void remove(){
        getThreadLocal().remove();
    }
}
