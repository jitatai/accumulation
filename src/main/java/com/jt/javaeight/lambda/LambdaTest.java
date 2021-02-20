package com.jt.javaeight.lambda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class LambdaTest {

    @Test
    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";
        // 构造一个匿名内部类对象
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
                System.out.println(str);
            }
        };

        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);

        r = () -> {
            System.out.println(this);
            System.out.println(str);
        };

        new Thread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }

}