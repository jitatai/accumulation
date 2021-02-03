package com.jt.accumulation.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 15:09
 */
public class BaseDao<T> {
    T getUser(T t){
        return t;
    }

    List<T> getUserList(T t){
        return new ArrayList<T>();
    }
}
