package com.jt.projects.aop.service;

import com.jt.projects.aop.entity.dto.UserLogDTO;

public interface UserLogService {

    /**
     * 插入用户操作日志
     *
     * @param userLogDTO
     * @return
     */
    Boolean addSysLog(UserLogDTO userLogDTO);

}
