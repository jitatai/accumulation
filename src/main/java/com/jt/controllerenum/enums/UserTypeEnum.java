package com.jt.controllerenum.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.controllerenum.controller.IEnum;
import com.jt.controllerenum.pojo.UserDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@Getter
public enum UserTypeEnum implements IEnum<String> {

    STUDENT(1, "学生"),
    TEACHER(2, "老师"),
    ;

    private final Integer type;
    @MyJsonCreator
    private final String desc;

    UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.desc;
    }


    /**
     * 静态方法+@JsonCreator指定根据哪个字段反序列化
     *
     * @param desc
     * @return
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserTypeEnum getEnum(String desc) {
        for (UserTypeEnum item : values()) {
            if (item.getDesc().equals(desc)) {
                log.info("进来了, desc:{}, item:{}", desc, item.toString());
                return item;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        // 模拟Postman发送JSON请求
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\n" +
                "    \"name\": \"bravoPostJson\",\n" +
                "    \"age\": 18,\n" +
                "    \"userType\": \"老师\"\n" +
                "}";
        System.out.println(json);

        // 请求：反序列化
        UserDTO userDTO = objectMapper.readValue(json, UserDTO.class);
        System.out.println(userDTO);

        // 响应：序列化
        String returnJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDTO);
        System.out.println(returnJson);

    }

    @Override
    public String toString() {
        return "UserTypeEnum{" +
                "type=" + type +
                ", desc='" + desc + '\'' +
                '}';
    }
}