package com.jt.projects.entitylayering.entity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/25 11:07
 */
@Data
@AllArgsConstructor
public class UserDO implements Serializable {
    private Long id;
    private String name;
    private DepartmentDO department;
}
