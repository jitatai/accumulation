package com.jt.web.crossorigin.controller;

import com.jt.web.crossorigin.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 */
@RestController
public class UserCrossOriginController {

    @GetMapping(value = "/getUser/{id}")
    @CrossOrigin("http://localhost:7070")
    public User getUser(@PathVariable("id") Long id) {
        // id没用上，就是演示一下@PathVariable注解
        System.out.println("id:" + id);

        User user = new User();
        user.setName("bravo");
        user.setAge(18);
        user.setAddress("wenzhou");

//        return user;
        return user;
    }
}