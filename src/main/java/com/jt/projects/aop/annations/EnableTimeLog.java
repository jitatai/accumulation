package com.jt.projects.aop.annations;

import com.jt.projects.aop.aspects.APITimeLogAspect;
import org.springframework.context.annotation.Import;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:15
 * 第一步 引入aop切面依赖
 */
@MethodClassRuntime
@Import(APITimeLogAspect.class)
public @interface EnableTimeLog {

}
