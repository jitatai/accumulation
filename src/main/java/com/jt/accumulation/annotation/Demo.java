package com.jt.accumulation.annotation;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/1/29 15:53
 * 使用注解
 */
@MyAnnotation(intValue = 1,longValue = 2L,stringArrValue = "2",intArrValue = {2},enumValue = MyAnnotation.CityEnum.BEIJING,
        annotationValue = @MyAnnotation.MyAnnotation2,
        stringValue = ":")
public class Demo {
}
