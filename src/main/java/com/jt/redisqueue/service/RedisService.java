package com.jt.redisqueue.service;

import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/2 10:58
 */
public interface RedisService<T> {
    /**
     * 向队列中存入信息
     * @param key 队列名称
     * @param value 要存入的消息
     */
    void pushQueue(String key,T value);

    /**
     * 从队列中取出消息
     * @param key 自定义队列名称
     * @param timeout 最长阻塞等待时间
     * @param timeUnit 时间单位
     */
    T popQueue(String key, Long timeout, TimeUnit timeUnit);
}
