package com.jt.projects.results;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/23 15:47
 */
public class TestSerializable {


    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.valueToTree(new User()).toPrettyString();
        System.out.println(s);
    }
}
@Data
class User{
    int age;
}
