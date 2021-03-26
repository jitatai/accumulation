package com.jt.projects.aop.entity;

import lombok.Data;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 17:08
 */
@Data
public class LogEntity {
    private Long timeConsuming;
    private String url;
    private String methodName;
    private String requestArgs;
    private String remoteAddress;
}
