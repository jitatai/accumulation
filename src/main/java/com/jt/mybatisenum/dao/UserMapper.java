package com.jt.mybatisenum.dao;

import com.jt.mybatisenum.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author qiyu
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户
     *
     * @param userDO
     */
    void insertUser(UserDO userDO);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserDO selectUserById(@Param("id") Long id);
}