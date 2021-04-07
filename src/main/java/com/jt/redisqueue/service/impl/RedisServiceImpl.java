package com.jt.redisqueue.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.redisqueue.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/2 11:03
 */
@Service
public class RedisServiceImpl<T> implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void pushQueue(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key,objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T popQueue(String key, Long timeout, TimeUnit timeUnit) {
        return (T) redisTemplate.opsForList().rightPop(key,timeout,timeUnit);
    }
}
