package com.jt.accumulation.practice;

import com.jt.spring.listener.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/20 10:09
 */
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;


    @Test
    public void testOrderService() {
        orderService.order();
    }
}
