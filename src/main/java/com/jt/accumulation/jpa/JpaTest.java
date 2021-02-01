package com.jt.accumulation.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 10:23
 */
public class JpaTest {
    public static void main(String[] args) throws IllegalAccessException {
        new C();
        UserDao userDao = new UserDao();
        userDao.add(new User("123",23));
    }
}
class A<T>{
    A(){
        Class<? extends A> subClass = this.getClass();
        ParameterizedType subClassGenericSuperclass = (ParameterizedType) subClass.getGenericSuperclass();
        Type[] actualTypeArguments = subClassGenericSuperclass.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
    }
}
class B extends A<String>{

}
class C extends A<Integer>{

}