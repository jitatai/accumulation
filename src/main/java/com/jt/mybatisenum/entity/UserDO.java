package com.jt.mybatisenum.entity;

/**
 * @author qiyu
 * @date 2020-09-26 09:56
 */

import com.jt.mybatisenum.enumj.WeekDayEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserDO {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 休息日，实际数据库字段是tinyint或varchar
     */
    private WeekDayEnum restDay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Boolean deleted;
}