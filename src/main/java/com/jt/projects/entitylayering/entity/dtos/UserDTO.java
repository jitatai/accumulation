package com.jt.projects.entitylayering.entity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:07
 */
@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private DepartmentDTO department;
}
