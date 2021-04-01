package com.jt.accumulation.practice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/1 15:35
 */
@Slf4j
@SpringBootTest
public class AsyncNotifyTest {

    @Test
    public void testAsyncNotify() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        log.info("上班打卡成功");

        TimeUnit.SECONDS.sleep(2);
        CompletableFuture.runAsync(()->{
            log.info("{}回家。。。。","我");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}回家记录存入数据库。。。","我");
        });
        log.info("打卡消耗了我{}秒",System.currentTimeMillis()-startTime);
        TimeUnit.SECONDS.sleep(3);
    }
}
