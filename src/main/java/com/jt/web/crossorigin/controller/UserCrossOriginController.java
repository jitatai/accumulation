package com.jt.web.crossorigin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 */
@RestController
public class UserCrossOriginController {

    @GetMapping(value = "/getCodeMsg")
    public String getUser() throws JsonProcessingException {
        String data = HttpRequest.sendGet("http://191.168.0.95:9000/API/service/MakeCode", "");
        System.out.println(data);
        return data;
    }
}