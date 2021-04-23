package com.jt.design_patterns.chains.handlers.verify;

import java.lang.annotation.*;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 15:15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Order {
    public int value() default 0;
}
