package com.jt.projects.aop.annations;

import com.jt.projects.aop.aspects.ApiLogAspect;
import org.springframework.context.annotation.Import;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:59
 */
@MethodClassRuntime
@Import(ApiLogAspect.class)
public @interface EnableLogRecord {

}
