package com.jt.design_patterns.chains.handlers.verify;

import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 13:41
 */
public interface VerifyHandler {
    /**
     * 调用链验证接口
     * @param list 待验证数据
     * @return 过滤后数据
     */
    List<Object> verify(List<Object> list);

    /**
     * 使用Spring自动注入到调用链时执行顺序
     * @return 权重
     */
    int executionOrder();

/*
    //**
     * 是否顺序排序
     * @return true 按权重从小到大执行 false 从大到小
     *//*
    boolean isAsc();
    */
}
