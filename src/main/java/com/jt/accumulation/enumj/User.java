package com.jt.accumulation.enumj;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/3 14:10
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private MemberEnum memberEnum;
}
