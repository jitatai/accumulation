package com.jt.redisqueue.config;

import com.jt.redisqueue.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/2 13:49
 */
@Slf4j
//@Component
public class ListenerRedisQueueConsume implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    RedisService<Object> redisService;
    public static final String ORDER_MESSAGE = "order_message";

    private void consumeMessage() {
        while (true){
            Object order = redisService.popQueue(ORDER_MESSAGE, 5L, TimeUnit.SECONDS);
            log.info("applicationListener每隔5秒循环获取，期间for循环阻塞");
            if (order != null){
                log.info("order: {}",order);
            }
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("applicationListener");
        // 2.启动消费者，取出订单消息，逐一取出
        CompletableFuture.runAsync(this::consumeMessage);
    }
}
