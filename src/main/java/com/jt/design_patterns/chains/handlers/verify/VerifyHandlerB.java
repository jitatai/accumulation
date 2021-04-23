package com.jt.design_patterns.chains.handlers.verify;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 13:49
 */
@Component
@Order(1)
public class VerifyHandlerB implements VerifyHandler {
    @Override
    public List<Object> verify(List<Object> list) {
        list.add("我是VerifyHandlerB");
        return list;
    }

    @Override
    public int executionOrder() {
        return 3;
    }
}
