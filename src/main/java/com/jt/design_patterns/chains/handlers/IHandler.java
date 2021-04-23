package com.jt.design_patterns.chains.handlers;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/22 10:07
 */
public interface IHandler {
    boolean handle();
}

class HandlerA implements IHandler{
    @Override
    public boolean handle() {
        System.out.println("处理器A...");
        return true;
    }
}

class HandlerB implements IHandler{
    @Override
    public boolean handle() {
        System.out.println("处理器B...");
        return true;
    }
}

class HandlerChain {
    List<IHandler> chains = new ArrayList<>();

    boolean addHandlers(List<IHandler> handlers){
        return chains.addAll(handlers);
    }

    /**
     * 使用任一匹配 终止内部迭代终止
     * @return
     */
    boolean handle(){
        AtomicBoolean result = new AtomicBoolean(true);

        chains.stream().anyMatch(handler -> {
            result.set(handler.handle());
            return  !result.get();
        });

        return result.get();
    }

    public static void main(String[] args) {
        HandlerChain chains = new HandlerChain();
        chains.addHandlers(Lists.newArrayList(new HandlerA(),new HandlerB()));
        chains.handle();
    }
}
