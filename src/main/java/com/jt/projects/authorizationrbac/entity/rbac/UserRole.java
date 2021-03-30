package com.jt.projects.authorizationrbac.entity.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/30 14:05
 */
@TableName("t_user_role")
@Getter
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
}
