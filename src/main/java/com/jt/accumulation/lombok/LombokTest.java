package com.jt.accumulation.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/1/25 15:39
 */
@Slf4j
public class LombokTest {

    @Test
    public void lombokTest(){
       LombokEntity entity = new LombokEntity();
       entity.setName("lombok");
       entity.setType(2);
       log.info("lombok的名字：{}，属性：{}",entity.getName(),entity.getType());
    }

    @Test
    public void lombokTest2(){
        LombokEntity entity = LombokEntity.newInstance("lombok").setName("lombok2").setType(2);
        log.info("lombok的名字：{}，属性：{}",entity.getName(),entity.getType());
    }


}
