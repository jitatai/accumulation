package com.jt.controllerenum.controller;

/**
 * 统一的枚举接口
 *
 * @author qiyu
 */
public interface IEnum<T> {

    /**
     * 强制指定按哪个字段进行反序列化
     *
     * @return
     */
    T getValue();

}