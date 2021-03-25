package com.jt.projects.entitylayering.service.impl;

import com.jt.projects.entitylayering.entity.dos.DepartmentDO;
import com.jt.projects.entitylayering.entity.dos.UserDO;
import com.jt.projects.entitylayering.entity.dtos.UserDTO;
import com.jt.projects.entitylayering.entity.vos.DepartmentVO;
import com.jt.projects.entitylayering.entity.vos.UserVO;
import com.jt.projects.entitylayering.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:13
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserVO getUserById(UserDTO userDTO) {
        List<UserDO> userDOList = Arrays.asList(new UserDO(1L,"jiatai",new DepartmentDO(1L,"资管")));
        if (CollectionUtils.isEmpty(userDOList)){
            return null;
        }
        return Optional.ofNullable(userDOList.get(0)).map(userDO -> new UserVO(1L,"jiatai",new DepartmentVO(1L,"资管")))
                .orElse(null);
    }
}
