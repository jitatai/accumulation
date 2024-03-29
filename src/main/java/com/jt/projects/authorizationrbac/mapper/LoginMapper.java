package com.jt.projects.authorizationrbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.projects.authorizationrbac.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/29 15:27
 */
@Mapper
public interface LoginMapper extends BaseMapper<User> {
}
