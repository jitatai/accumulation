package com.jt.projects.authorizationrbac.entity.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/30 14:03
 */
@TableName("t_user")
@Getter
public class User {
    private Long id;
    @NotNull(message = "name 不能为空")
    private String name;
    @NotNull(message = "password 不能为空")
    private String password;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private int deleted;
}
