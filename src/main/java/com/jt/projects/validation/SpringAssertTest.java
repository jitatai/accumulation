package com.jt.projects.validation;

import org.springframework.util.Assert;

public class SpringAssertTest {
    public static void main(String[] args) {
        String name = "1";
        Assert.hasText(name,"名字不能为空");
        Integer age = null;
        StringBuilder errorMsg = new StringBuilder();
        Assert.isNull(age,errorMsg.append("请填写age").toString());
        Integer height = 180;
        Assert.isTrue(height > 200,"身高低于200");
    }
}
