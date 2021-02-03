package com.jt.accumulation.enumj;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/2 15:11
 */
public class EmployeeTest {
    static String A = "A";
    static String B = "B";
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setRestDay(WeekDay.MONDAY);
        WeekDay[] values = WeekDay.getVALUES();
        // 如果WeekDay的Values方法不用克隆的话 会引起Values的值发生变化。
        values[0] = WeekDay.FRIDAY;

        for (WeekDay value : WeekDay.getVALUES()) {
            System.out.println(value.getDesc());
        }

        switch (A){
            case "A" :
        }
    }
}
