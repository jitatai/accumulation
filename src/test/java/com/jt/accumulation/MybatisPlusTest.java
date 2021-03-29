package com.jt.accumulation;

import com.jt.projects.aop.entity.dto.UserLogDTO;
import com.jt.projects.aop.service.UserLogService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/26 11:15
 */
@SpringBootTest
@MapperScan("com.jt.projects.aop.dao")
public class MybatisPlusTest {
    @Resource
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
        userLogService.addSysLog(userLogDTO);
    }
}
