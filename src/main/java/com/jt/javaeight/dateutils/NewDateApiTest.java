package com.jt.javaeight.dateutils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class NewDateApiTest {
    public static void main(String[] args) {

        // 旧的方式，可以new
        Date date = new Date();
        System.out.println("old api" + date);

        // 新的方式，只能通过给定的方法获取
        LocalDate newDate = LocalDate.now(); 			 // 日期 2020-12-12
        LocalTime newTime = LocalTime.now(); 			 // 时间 16:30:00:000
        LocalDateTime newDateTime = LocalDateTime.now(); // 日期+时间 2020-12-12T16:30:00.000
        System.out.println("newDate" + newDate);
        System.out.println("newTime" + newTime);
        System.out.println("newDateTime" + newDateTime);

        // 还可以组合哟
        LocalDateTime combineDateTime = LocalDateTime.of(newDate, newTime);
        System.out.println("combineDateTime" + combineDateTime);

        // 创建指定时间
        LocalDate customDate = LocalDate.of(2020, 11, 5);
        LocalTime customTime = LocalTime.of(16, 30, 0);
        LocalDateTime customDateTime = LocalDateTime.of(2020, 11, 5, 16, 30, 0);
        System.out.println("customDate" + customDate);
        System.out.println("customTime" + customTime);
        System.out.println("customDateTime" + customDateTime);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime minus = now.minus(1, ChronoUnit.YEARS);
        System.out.println(minus);

        System.out.println("计算当天的00点和24点：");
        LocalDateTime todayBegin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.out.println("todayBegin:" + todayBegin);
        System.out.println("todayEnd:" + todayEnd + "\n");

        // 修改时间 with
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime with = localDateTime.with(ChronoField.DAY_OF_MONTH, 3);
        System.out.println(with);

        // 比较时间 isAfter isBefore isEqual
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime after = nowTime.plusSeconds(1);

        System.out.println(nowTime.isAfter(after));
        System.out.println(nowTime.isBefore(after));
        System.out.println(nowTime.isEqual(after));

        ZonedDateTime zonedDateTime = nowTime.atZone(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime time = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);
        System.out.println(time);
        LocalDateTime localDateTime1 = time.toLocalDateTime();
        System.out.println(nowTime);
        System.out.println(localDateTime1);

        // localDateTime 和 Date 互转
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime1 = dateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime1.toInstant();
        System.out.println(instant);
        Date from = Date.from(instant);
        Instant instant1 = from.toInstant();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
//        ZonedDateTime from1 = ZonedDateTime.from(instant);
//        System.out.println(from1);
        System.out.println(from);

        System.out.println(System.currentTimeMillis());
        long result = now.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(result);

        localDateTime = LocalDateTime.ofEpochSecond(result, 0, ZoneOffset.ofHours(8));
        System.out.println(localDateTime);

        System.out.println("格式化前:" + now);
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("格式化后:" + format);

        // 反格式化
        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(parse);


    }

}