package com.jt.accumulation.practice;

import com.jt.redisqueue.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/2 11:19
 */
@SpringBootTest
@Slf4j
public class RedisQueueTest {
    @Autowired
    RedisService<Object> redisService;
    public static final String ORDER_MESSAGE = "order_message";

    @Test
    public void testRedisQueue() throws Exception {
        // 1.订单服务 模拟第一次消息
        orderService("jiatai",10086L);


        // 2.启动消费者，取出订单消息，逐一取出
        CompletableFuture.runAsync(this::consumeMessage);

        // 3.模拟10s后 再发一条消息 模拟第二次消息
        TimeUnit.SECONDS.sleep(10);
        orderService("jiatai",10088L);

        // 4.等待一会儿，观察第二条消息
        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    public void testListAdd() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(null);
        orderList.add(null);
        System.out.println(orderList.size());
        for (Order order : orderList) {
            System.out.println(order);
        }
    }

    private void orderService(String name, long id) {
        redisService.pushQueue(ORDER_MESSAGE,new Order(name,id));
    }

    private void consumeMessage() {
        while (true){
            Object order = redisService.popQueue(ORDER_MESSAGE, 2L, TimeUnit.SECONDS);
            log.info("每隔5秒循环获取，期间for循环阻塞");
            if (order != null){
                log.info("order: {}",order);
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private String username;
        private Long resumeId;
    }
}
