package com.jt.accumulation.enumj;

import lombok.Data;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/2 15:12
 */
@Data
public class WeekDay {
    public static final WeekDay MONDAY;
    public static final WeekDay TUESDAY;
    public static final WeekDay WEDNESDAY;
    public static final WeekDay THURSDAY;
    public static final WeekDay FRIDAY;
    public static final WeekDay SATURDAY;
    public static final WeekDay SUNDAY;

    private static final WeekDay[] VALUES;

    static {
        // 之前说过，final字段赋值有三种形式，现在我们换成静态代码块赋值
        MONDAY = new WeekDay(1, "星期一");
        TUESDAY = new WeekDay(2, "星期二");
        WEDNESDAY = new WeekDay(3, "星期三");
        THURSDAY = new WeekDay(4, "星期四");
        FRIDAY = new WeekDay(5, "星期五");
        SATURDAY = new WeekDay(6, "星期六");
        SUNDAY = new WeekDay(7, "星期日");
        // 在加载类时就收集所有的WeekDay对象
        VALUES = new WeekDay[]{
                MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        };
    }

    public static WeekDay[] getVALUES() {
        return VALUES;
    }

    /**
     * 校验前端传入的code是否合法
     *
     * @param code
     * @return
     */
    public static WeekDay of(Integer code) {
        for (WeekDay weekDay : VALUES) {
            if (weekDay.code.equals(code)) {
                return weekDay;
            }
        }
        // 如果根据code找不到对应的WeekDay，说明code范围不对，是非法的
        throw new IllegalArgumentException("Invalid Enum code:" + code);
    }

    private WeekDay(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;
    private final String desc;
}
