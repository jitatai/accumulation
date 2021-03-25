package com.jt.projects.entitylayering.dao;

import com.jt.mybatisenum.entity.UserDO;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:12
 */
public interface UserMapper {
    UserDO getUserByID(Long id);
}
