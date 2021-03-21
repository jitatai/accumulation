package com.jt.projects.validation.controller;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {
    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull(message = "age不能为空")
    @Max(value = 30,message = "程序员年龄最大不能超过30岁")
    @Min(value = 16,message = "程序员年龄最小不能低于16岁")
    private Integer age;
    @NotNull(message = "部门不能为空")
    @Valid
    private Department department;

}
class Department{
    @NotNull(message = "id不能为空")
    Long id;
}
