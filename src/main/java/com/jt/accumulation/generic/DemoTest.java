package com.jt.accumulation.generic;

import com.jt.accumulation.jpa.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 15:11
 */
public class DemoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(new User("", 1));
        List<User> userList = userDao.getUserList(new User("", 2));

        // List<String> 存入Integer对象
        List<String> list = new ArrayList<>();
        list.add("wo shi string");
        try {
            Method add = List.class.getMethod("add", Object.class);
            add.invoke(list,1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(list);

        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(1.11111111111);
        System.out.println(doubles);
        List<? extends Number> numbers = doubles;
        List<Integer> integerList = (List<Integer>) numbers;
        System.out.println(integerList);


    }

}
