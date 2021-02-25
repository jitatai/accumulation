package com.jt.web.crossorigin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/25 9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
    private String address;
}
