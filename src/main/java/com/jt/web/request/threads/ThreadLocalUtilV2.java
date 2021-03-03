package com.jt.web.request.threads;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiatai
 */
public class ThreadLocalUtilV2 {
    private enum ThreadLocalEnum{
        /**
         *
         */
        THREAD_LOCAL_ENUM;
        private ThreadLocal<Map<String,Object>> threadLocal;
        ThreadLocalEnum(){
            threadLocal = new MapThreadLocal();
            /*threadLocal = new ThreadLocal(){
                @Override
                protected Object initialValue() {
                    return new HashMap<>();
                }
            };*/
        }
    }
    public static ThreadLocal<Map<String,Object>> getThreadLocal(){
        return ThreadLocalEnum.THREAD_LOCAL_ENUM.threadLocal;
    }
    static class MapThreadLocal extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return new HashMap<String,Object>(8);
        }
    }

    /**
     * 获取当前线程中以ThreadLocal为key的value
     * @return
     */
    public static Map<String,Object> getCurrentMap(){
        return getThreadLocal().get();
    }

    public static void put(String key,Object value){
        getCurrentMap().put(key,value);
    }

    public static Object get(String key){
        return getCurrentMap().get(key);
    }

    public static void remove(String key){
        getCurrentMap().remove(key);
    }

    /**
     * 把当前线程的ThreadLocalMap中以ThreadLocal为key的value清除
     */
    public static void clear(){
        getThreadLocal().remove();
    }
}
