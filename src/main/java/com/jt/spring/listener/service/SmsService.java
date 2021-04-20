package com.jt.spring.listener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/20 10:02
 */
@Service
@Slf4j
public class SmsService /*implements ApplicationListener<OrderSuccessEvent> */{

    @EventListener(OrderSuccessEvent.class)
    public void onApplicationEvent(OrderSuccessEvent event) {
        log.info(event.toString());
        this.sendMessage();
    }

    private void sendMessage() {
        log.info("发送短信给用户");
    }
}
