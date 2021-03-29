package com.jt.projects.aop.entity.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user_log")
public class UserLogDO {

    private Long id;

    /**
     * 本次操作的系统模块
     *
     * @see com.bravo.demo.aspect.ModuleEnum
     */
    private String moduleCode;
    /**
     * 操作类型
     *
     * @see com.bravo.demo.aspect.OperationEnum
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 操作人
     */
    private Long operatorId;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 操作内容
     */
    private String content;

}