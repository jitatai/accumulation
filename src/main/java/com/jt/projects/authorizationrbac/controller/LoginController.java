package com.jt.projects.authorizationrbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jt.projects.authorizationrbac.annotation.LoginRequired;
import com.jt.projects.authorizationrbac.annotation.PermissionRequired;
import com.jt.projects.authorizationrbac.constant.WebConstant;
import com.jt.projects.authorizationrbac.entity.User;
import com.jt.projects.authorizationrbac.enums.Logical;
import com.jt.projects.authorizationrbac.enums.UserType;
import com.jt.projects.authorizationrbac.mapper.LoginMapper;
import com.jt.projects.exceptionhandler.results.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/29 15:22
 */
@RestController
@RequestMapping("login")
@Validated
public class LoginController {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private HttpSession session;
    @PostMapping("/register")
    public Result<User> register(@RequestBody User userInfo) {
        int rows = loginMapper.insert(userInfo);
        if (rows > 0) {
            return Result.success(userInfo);
        }

        return Result.error("插入失败");
    }

    @PostMapping("login")
    public Result<User> login(@RequestBody User userInfo){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(User::getName,userInfo.getName());
        lambdaQueryWrapper.eq(User::getPassword,userInfo.getPassword());
        List<User> users = loginMapper.selectList(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(users)){
            return Result.error("用户名或密码错误");
        }
        session.setAttribute(WebConstant.CURRENT_USER_IN_SESSION, users.get(0));
        return Result.success(users.get(0));
    }

    @LoginRequired
    @GetMapping("/needLogin")
    public Result<String> needLogin() {
        return Result.success("if you see this, you are logged in.");
    }

    @GetMapping("/needNotLogin")
    public Result<String> needNotLogin() {
        return Result.success("if you see this, you are logged in.");
    }

    @PermissionRequired(userType = {UserType.ADMIN,UserType.TEACHER}, logical = Logical.OR)
    @GetMapping("/needPermission")
    public Result<String> needPermission() {
        return Result.success("if you see this, you has the permission.");
    }
}
