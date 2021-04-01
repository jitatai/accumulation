package com.jt;

import com.jt.projects.aop.annations.EnableLogRecord;
import com.jt.projects.aop.annations.EnableTimeLog;
import com.jt.projects.aop.annations.EnableUserLog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableTimeLog
@EnableLogRecord
@EnableUserLog
@MapperScan("com.jt.*.*.mapper")
public class AccumulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccumulationApplication.class, args);
    }

}
