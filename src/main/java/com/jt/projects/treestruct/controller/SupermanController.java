package com.jt.projects.treestruct.controller;

import com.jt.projects.treestruct.Service.SupermanService;
import com.jt.projects.treestruct.entity.Superman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupermanController {

    @Autowired
    private SupermanService supermanService;

    @RequestMapping("/getAllSuperman")
    public List getAllSuperman() {
        // supermanService才是重点，接下来演示三种全量嵌套查询的方法
        List<Superman> list = supermanService.getAllSuperman();
        return list;
    }

    @RequestMapping("/for")
    public List getAllSupermanByFor() {
        // 通过 for循环读取
        List<Superman> list = supermanService.getAllSupermanByFor();
        return list;
    }

    @RequestMapping("map")
    public List<Superman> map(){
        return supermanService.mapForAll();
    }
}