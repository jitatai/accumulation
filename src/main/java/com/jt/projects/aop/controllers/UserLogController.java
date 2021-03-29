package com.jt.projects.aop.controllers;

import com.jt.controllerenum.pojo.UserDTO;
import com.jt.projects.aop.annations.UserLog;
import com.jt.projects.exceptionhandler.results.Result;
import com.jt.web.request.threads.ThreadLocalUtilV4;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/26 14:04
 */
@RestController
public class UserLogController {
    @PostMapping("/saveUser")
    @UserLog
    public Result saveUser(@RequestBody UserDTO userDTO){
        ThreadLocalUtilV4.put("userDTO",userDTO);
        return Result.success();
    }
}
