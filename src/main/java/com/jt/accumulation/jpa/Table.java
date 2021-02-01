package com.jt.accumulation.jpa;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/1 10:50
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
