package com.jt.projects.authorizationrbac.entity.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/30 14:04
 */
@TableName("t_permission")
@Getter
public class Permission {
    private Long id;
    private String module;
    private String name;
    private String method;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private int deleted;
}
