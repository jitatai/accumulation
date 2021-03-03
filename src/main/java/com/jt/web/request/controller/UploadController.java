package com.jt.web.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/springUpload")
    public void upload(String name, Integer age,@RequestPart MultipartFile file) {
        log.info("name:{}, age:{}, fileName:{}", name, age, file.getOriginalFilename());
    }

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> null);
    @GetMapping("/wrong")
    public Map<String,String> wrong(@RequestParam("userId")Integer userId){
        Integer before = threadLocal.get();
        threadLocal.set(userId);
        Integer after = threadLocal.get();
        Map<String,String> result = new HashMap<>();
        result.put("before",before+Thread.currentThread().getName());
        result.put("after",after+Thread.currentThread().getName());
        return result;
    }

    @GetMapping("/right")
    public Map<String,String> right(@RequestParam("userId")Integer userId){
        Map<String,String> result = new HashMap<>();
        try {
            Integer before = threadLocal.get();
            threadLocal.set(userId);
            Integer after = threadLocal.get();
            result.put("before",before+Thread.currentThread().getName());
            result.put("after",after+Thread.currentThread().getName());
        }finally {
            threadLocal.remove();
        }
        return result;
    }
}