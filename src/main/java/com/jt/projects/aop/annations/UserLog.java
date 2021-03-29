package com.jt.projects.aop.annations;

import com.jt.projects.aop.enums.ModuleEnum;
import com.jt.projects.aop.enums.OperationEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/26 13:32
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLog {
    /**
     * 所属模块名
     * @return
     */
    ModuleEnum module() default ModuleEnum.USER;

    /**
     * 操作类型
     * @return
     */
    OperationEnum operationType() default OperationEnum.ADD;

    /**
     * 操作标题
     * @return
     */
    String title() default "用户模块";
}
