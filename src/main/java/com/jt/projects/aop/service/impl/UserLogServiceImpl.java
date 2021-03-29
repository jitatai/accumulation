package com.jt.projects.aop.service.impl;

import com.jt.projects.aop.dao.UserLogMapper;
import com.jt.projects.aop.entity.dos.UserLogDO;
import com.jt.projects.aop.entity.dto.UserLogDTO;
import com.jt.projects.aop.service.UserLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiatai.hu
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Resource
    private UserLogMapper userLogMapper;

    @Override
    public Boolean addSysLog(UserLogDTO userLogDTO) {
        UserLogDO userLogDO = new UserLogDO();
        userLogDO.setModuleCode(userLogDTO.getModuleCode());
        userLogDO.setTitle(userLogDTO.getTitle());
        userLogDO.setOperatorId(userLogDTO.getOperatorId());
        userLogDO.setOperateTime(userLogDTO.getOperateTime());
        userLogDO.setContent(userLogDTO.getContent());
        userLogDO.setType(userLogDTO.getType());
        return userLogMapper.insert(userLogDO) > 0;
    }

}