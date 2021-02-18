package com.jt.controllerenum.controller;

import com.jt.controllerenum.pojo.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/web/user")
public class UserController {

    @GetMapping("/get")
    public void get(UserDTO userDTO) {
        log.info(userDTO.toString());
    }

    @PostMapping("/postForm")
    public void postForm(UserDTO userDTO) {
        log.info(userDTO.toString());
    }

    @PostMapping("/postJson")
    public void postJson(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
    }

}