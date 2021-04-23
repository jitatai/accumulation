package com.jt.design_patterns.chains.handlers.verify;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 13:49
 */
@Component
@Order(2)
public class VerifyHandlerC implements VerifyHandler {
    @Override
    public List<Object> verify(List<Object> list) {
        list.add("我是VerifyHandlerC");
        return list;
    }

    @Override
    public int executionOrder() {
        return 2;
    }
}
