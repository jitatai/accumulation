package com.jt.design_patterns.chains.handlers.verify;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 13:52
 */
@Component
public class VerifyChain implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Set<VerifyHandler> verifyHandlerSet;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, VerifyHandler> beansOfType = applicationContext.getBeansOfType(VerifyHandler.class);

        // 按权重顺序加入调用链中
        verifyHandlerSet = beansOfType.values().stream()
                .sorted(Comparator.comparingInt(VerifyHandler::executionOrder))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 获取处理链管理类上按何种顺序处理
        Asc asc = AnnotationUtils.findAnnotation(VerifyChain.class, Asc.class);

        verifyHandlerSet = beansOfType.values().stream()
                .sorted(((o1, o2) -> {
                    Order order1 = AnnotationUtils.findAnnotation(o1.getClass(), Order.class);
                    Order order2 = AnnotationUtils.findAnnotation(o2.getClass(), Order.class);
                    assert order1 != null;
                    assert order2 != null;
                    if (asc == null){
                        return order2.value() - order1.value();
                    }
                    return order1.value() - order2.value();
                }))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public List<Object> verify(List<Object> sourceList){
        for (VerifyHandler verifyHandler : verifyHandlerSet) {
            sourceList = verifyHandler.verify(sourceList);
            if (CollectionUtils.isEmpty(verifyHandlerSet)){
                return sourceList;
            }
        }

        return sourceList;
    }
}
