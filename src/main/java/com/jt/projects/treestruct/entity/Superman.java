package com.jt.projects.treestruct.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_superman")
public class Superman {
    private Integer id;
    private String name;
    @TableField(exist = false)
    private Boolean isParent;
    private Boolean open;
    private Integer pid;
    // 前端JS对象的children数组(对应List)里存的还是JS对象（对应Superman），所以是List<Superman>
    @TableField(exist = false)
    private List<Superman> children = new ArrayList<>();
}