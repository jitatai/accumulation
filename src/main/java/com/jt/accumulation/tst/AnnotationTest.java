package com.jt.accumulation.tst;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 10:08
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Class<UserDao> userDaoClass = UserDao.class;
        try {
            UserDao userDao = userDaoClass.newInstance();

            Method[] methods = userDaoClass.getMethods();

            List<Method> beforeMethod = new ArrayList<>();
            List<Method> afterMethod = new ArrayList<>();
            List<Method> testMethod = new ArrayList<>();

            for (Method method : methods) {
                if (method.isAnnotationPresent(MyBefore.class)){
                    beforeMethod.add(method);
                }else if(method.isAnnotationPresent(MyAfter.class)){
                    afterMethod.add(method);
                }else if(method.isAnnotationPresent(MyTest.class)){
                    testMethod.add(method);
                }
            }

            for (Method method : testMethod) {
                for (Method before : beforeMethod) {
                    before.invoke(userDao,null);
                }
                method.invoke(userDao,null);
                for (Method after : afterMethod) {
                    after.invoke(userDao,null);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
