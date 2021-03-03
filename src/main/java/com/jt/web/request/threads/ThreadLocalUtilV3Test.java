package com.jt.web.request.threads;

/**
 * @author qiyu
 * @date 2020-09-17 13:29
 */
public class ThreadLocalUtilV3Test {

    public static void main(String[] args) {

        ThreadLocalUtilV3.put("mainKey", "mainValue");

        new Thread(()->{
            ThreadLocalUtilV3.put("threadKey", "threadValue");

            System.out.println("get main value in thread:" + ThreadLocalUtilV3.get("mainKey"));
            System.out.println("get thread value in thread:" + ThreadLocalUtilV3.get("threadKey"));
        }).start();

        assert ThreadLocalUtilV3.get("threadKey") != null;

        System.out.println("get thread value in main:" + ThreadLocalUtilV3.get("threadKey"));
        System.out.println("get main value in main:" + ThreadLocalUtilV3.get("mainKey"));

    }

}