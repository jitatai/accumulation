package com.jt.mybatisenum.enumj;

import com.jt.mybatisenum.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum WeekDayEnum {
    MONDAY(1,"星期一"),
    TUESDAY(2,"星期二"),
    WEDNESDAY(3,"星期三"),
    THURSDAY(4,"星期四"),
    FRIDAY(5,"星期五"),
    SATURDAY(6,"星期六"),
    SUNDAY(7,"星期日");

    WeekDayEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;
    @EnumValue
    private final String desc;
}