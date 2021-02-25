package com.jt.web.request.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/springUpload")
    public void upload(String name, Integer age,@RequestPart MultipartFile file) {
        log.info("name:{}, age:{}, fileName:{}", name, age, file.getOriginalFilename());
    }
}