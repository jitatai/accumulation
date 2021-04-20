package com.jt.spring.listener.service;

import org.springframework.context.ApplicationEvent;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/20 10:01
 * 自定义事件源
 */
public class OrderSuccessEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public OrderSuccessEvent(Object source) {
        super(source);
    }
}
