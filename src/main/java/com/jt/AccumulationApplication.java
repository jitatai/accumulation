package com.jt;

import com.jt.projects.aop.annations.EnableLogRecord;
import com.jt.projects.aop.annations.EnableTimeLog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableTimeLog
@EnableLogRecord
@MapperScan("com.jt.mybatisenum.dao")
public class AccumulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccumulationApplication.class, args);
    }

}
