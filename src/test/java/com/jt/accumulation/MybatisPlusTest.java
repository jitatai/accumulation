package com.jt.accumulation;

import com.jt.projects.aop.entity.dto.UserLogDTO;
import com.jt.projects.aop.service.UserLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/26 11:15
 */
@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    private UserLogService userLogService;


    @Test
    public void testInsert() {
        UserLogDTO userLogDTO = new UserLogDTO();
        userLogDTO.setContent("123");
        userLogDTO.setId(1L);
        userLogDTO.setModuleCode("123");
        userLogDTO.setOperateTime(new Date());
        userLogDTO.setTitle("321");
        userLogDTO.setOperatorId(21L);
        userLogDTO.setType(1);
        for (int i = 0; i < 5; i++) {
            if (i==3){
                throw new RuntimeException("ceshi");
            }
            userLogService.addSysLog(userLogDTO);
        }
    }
}
