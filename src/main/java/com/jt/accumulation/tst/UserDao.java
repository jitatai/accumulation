package com.jt.accumulation.tst;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 10:02
 */
public class UserDao {
    @MyBefore
    public void before(){
        System.out.println("开始初始化");
    }
    @MyAfter
    public void after(){
        System.out.println("开始销毁");
    }
    @MyTest
    public void testSave() {
        System.out.println("save...");
    }

    @MyTest
    public void testDelete() {
        System.out.println("delete...");
    }
}
