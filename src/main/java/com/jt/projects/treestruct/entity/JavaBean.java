package com.jt.projects.treestruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class JavaBean {
//    private Long id;
    private String name;
    private Boolean open;
    private Boolean isParent;
    private List<JavaBean> list;
}
