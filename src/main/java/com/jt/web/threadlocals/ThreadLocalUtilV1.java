package com.jt.web.threadlocals;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/3 17:02
 */
public class ThreadLocalUtilV1 {
    private enum ThreadLocalEnum{
        THREAD_LOCAL_ENUM;
        private ThreadLocal<Map<String,Object>> threadLocal;
        ThreadLocalEnum(){
            threadLocal = new ThreadLocal<>();
        }
    }

    static ThreadLocal<Map<String,Object>> getThreadLocal(){
        return ThreadLocalEnum.THREAD_LOCAL_ENUM.threadLocal;
    }

    public static <T> void put(String key,T value){
        Map<String, Object> map = getThreadLocal().get();
       if (map == null){
           map = new HashMap<>();
           getThreadLocal().set(map);
       }
       map.put(key,value);
    }

    public static Object get(String key){
        return getThreadLocal().get().get(key);
    }

    public static void remove(String key){
        getThreadLocal().get().remove(key);
    }

    public static void clear(){
        getThreadLocal().remove();
    }
}
