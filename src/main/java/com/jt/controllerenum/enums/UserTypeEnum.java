package com.jt.controllerenum.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    STUDENT(1, "学生"),
    TEACHER(2, "老师"),
    ;

    private final Integer type;
    private final String desc;

    UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}