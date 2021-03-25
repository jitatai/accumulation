package com.jt.projects.aliresults.annotations;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**元注解
 * @Target 使用位置 默认是各个位置都可以
 * @Documented 用于制作文档
 * @Retention 保留策略 保留到什么阶段 RUNTIME 反射是运行时调用 所以要求注解的信息必须保留到虚拟机将class文件加载到
 * 内存为止
 * @author Administrator
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RestController
public @interface CosmoController {
}
