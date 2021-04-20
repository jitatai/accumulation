package com.jt.spring.listener.service;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/20 10:01
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    private final ApplicationContext applicationContext;

    public OrderService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void order(){
        log.info("下单成功了...");
        applicationContext.publishEvent(new OrderSuccessEvent(this));
        log.info("主线程结束...");
    }
}
