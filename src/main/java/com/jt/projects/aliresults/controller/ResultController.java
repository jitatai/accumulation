package com.jt.projects.aliresults.controller;

import com.jt.projects.aliresults.annotations.CosmoController;
import com.jt.projects.aliresults.annotations.IgnoreCosmoResult;
import com.jt.projects.exceptionhandler.results.Result;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@CosmoController
public class ResultController {
    @RequestMapping("result")
    @IgnoreCosmoResult
    public User result(){
        return new User();
    }

    @RequestMapping("results")
    public Result<User> results(){
        return Result.success(new User());
    }

    @Data
    public class User{
        int age = 0;
        String name = "jiatai";
    }
}
