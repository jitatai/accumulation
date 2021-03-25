package com.jt.projects.entitylayering.dao;

import com.jt.mybatisenum.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:12
 */
@Mapper
public interface UserMapper {
    UserDO getUserByID(Long id);
}
