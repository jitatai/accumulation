package com.jt.controllerenum.controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.jt.controllerenum.factory.MyEnumConverterFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 把我们自定义的枚举转换器添加到Spring容器，Spring容器会把它加入到SpringMVC的拦截链路中
        registry.addConverterFactory(new MyEnumConverterFactory());
    }

    /**
     * 自定义JSON响应时枚举字段的序列化行为：调用toString()
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
}