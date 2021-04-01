package com.jt.accumulation.practice;

import com.jt.mybatisenum.dao.UserMapper;
import com.jt.mybatisenum.entity.UserDO;
import com.jt.mybatisenum.enumj.WeekDayEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AccumulationApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setName("MyBatis枚举测试");
        userDO.setAge(18);
        userDO.setRestDay(WeekDayEnum.FRIDAY);

        userMapper.insertUser(userDO);
    }

    @Test
    public void testSelect() {
        UserDO userDO = userMapper.selectUserById(1L);
        System.out.println(userDO);
    }


}
