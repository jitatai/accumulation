package com.jt.projects.entitylayering.controller;

import com.jt.projects.entitylayering.entity.dtos.UserDTO;
import com.jt.projects.entitylayering.entity.vos.UserVO;
import com.jt.projects.entitylayering.service.UserService;
import com.jt.projects.exceptionhandler.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:11
 */
@RestController
public class LayeringUserController {
    @Autowired
    private UserService userService;
    @RequestMapping("layering")
    public Result<UserVO> layering(UserDTO userDTO){
        return Result.success(userService.getUserById(userDTO));
    }
}
