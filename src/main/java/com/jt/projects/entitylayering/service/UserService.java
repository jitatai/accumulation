package com.jt.projects.entitylayering.service;

import com.jt.projects.entitylayering.entity.dtos.UserDTO;
import com.jt.projects.entitylayering.entity.vos.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:12
 */
@Service
public interface UserService {
    UserVO getUserById(UserDTO userDTO);
}
