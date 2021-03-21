package com.jt.projects.validation.controller;

import com.jt.projects.validation.lists.ValidList;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
// 使用Spring @Validated校验需要先声明
@Validated
public class ValidationController2 {
    @RequestMapping("test")
    public Object test(@NotNull(message = "id不能为空") Long id, @Length(max = 100,min = 10,message = "备注需要在10到100之间") String memo){
        return "test";
    }

    @RequestMapping(value = "getDtoTest")
    public Object getDtoTest(@Validated User user){
        return "操作成功";
    }

    @RequestMapping(value = "postTest",method = RequestMethod.POST)
    public Object postJsonTest(@Validated @RequestBody User user){
        return "操作成功";
    }

    @RequestMapping(value = "postListTest",method = RequestMethod.POST)
    public Object postListTest(@Validated @RequestBody ValidList<User> userList){
        return "操作成功";
    }
}
