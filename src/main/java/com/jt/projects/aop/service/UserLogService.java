package com.jt.projects.aop.service;

import com.jt.projects.aop.entity.dto.UserLogDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserLogService {

    /**
     * 插入用户操作日志
     *
     * @param userLogDTO
     * @return
     */
    Boolean addSysLog(UserLogDTO userLogDTO);

}
