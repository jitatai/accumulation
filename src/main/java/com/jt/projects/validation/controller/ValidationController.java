package com.jt.projects.validation.controller;

import com.jt.projects.validation.ValidationUtilsV3;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("validation")
public class ValidationController {
    @RequestMapping("test")
    public Object testValidation(String name,Integer age){
        ValidationUtilsV3.isNotNull(name,"姓名");
        ValidationUtilsV3.isLessThan0(age,"年龄");
        return "成功";
    }
}
