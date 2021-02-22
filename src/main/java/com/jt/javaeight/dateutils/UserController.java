package com.jt.javaeight.dateutils;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public UserPojo addUser(@RequestBody UserPojo userPojo) {
        userPojo.setBirthday(userPojo.getBirthday().plusDays(1));
        return userPojo;
    }

}

@Data
class UserPojo {

    private String name;
    private LocalDateTime birthday;

}