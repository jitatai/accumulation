package com.jt.projects.aliresults.annotations;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreCosmoResult {
}
