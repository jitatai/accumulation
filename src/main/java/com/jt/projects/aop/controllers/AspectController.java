package com.jt.projects.aop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 16:27
 */
@RestController
public class AspectController {
    @RequestMapping("aspect")
    public Object aspect(){
        for (int i = 0; i < 1000; i++) {
            int j = i;
        }
        return "nihao";
    }
}
