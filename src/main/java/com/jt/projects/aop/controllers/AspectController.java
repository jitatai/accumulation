package com.jt.projects.aop.controllers;

import com.jt.projects.aop.annations.ApiLog;
import com.jt.projects.aop.annations.IgnoreApiLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:27
 */
@RestController
@ApiLog
public class AspectController {
    @RequestMapping("aspect")
    public Object aspect(String userName){
        for (int i = 0; i < 1000; i++) {
            int j = i;
        }
        return userName + ":nihao";
    }

    @RequestMapping("ignore")
    @IgnoreApiLog
    public Object ignore(String userName){
        for (int i = 0; i < 1000; i++) {
            int j = i;
        }
        return userName + ":nihao";
    }
}
