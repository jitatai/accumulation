package com.jt.projects.authorizationrbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jt.projects.authorizationrbac.constant.WebConstant;
import com.jt.projects.authorizationrbac.entity.rbac.Permission;
import com.jt.projects.authorizationrbac.entity.rbac.Role;
import com.jt.projects.authorizationrbac.entity.rbac.RolePermission;
import com.jt.projects.authorizationrbac.entity.rbac.User;
import com.jt.projects.authorizationrbac.mapper.rbac.*;
import com.jt.projects.exceptionhandler.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/30 14:21
 */
@RestController
@RequestMapping("/rbac")
public class RbacController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName,user.getName());
        lambdaQuery.eq(User::getPassword,user.getPassword());
        List<User> userList = userMapper.selectList(lambdaQuery);
        if (CollectionUtils.isEmpty(userList)){
            return Result.error("用户名或密码错误");
        }
        // 登录成功后 session记录登录状态
        session.setAttribute(WebConstant.CURRENT_USER_IN_SESSION,user);
        // 记录当前用户 拥有的权限
        session.setAttribute(WebConstant.USER_PERMISSIONS,getUserPermissions(user.getId()));
        return Result.success(userList.get(0));
    }

    /**
     * 通过用户id 获取用户拥有的所有权限（即方法的url）
     * @param id
     * @return
     */
    private Set<String> getUserPermissions(Long id) {
        LambdaQueryWrapper<Role> lambdaQuery = Wrappers.lambdaQuery();
        List<Role> roleList = roleMapper.selectList(lambdaQuery);
        if (CollectionUtils.isEmpty(roleList)){
            return Collections.emptySet();
        }
        Set<Long> roleIdSet = roleList.stream().map(Role::getId).collect(Collectors.toSet());

        List<RolePermission> rolePermissionList = rolePermissionMapper.selectBatchIds(roleIdSet);
        if (CollectionUtils.isEmpty(rolePermissionList)){
            return Collections.emptySet();
        }
        Set<Long> permissionIdList = rolePermissionList.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissionIdList)){
            return Collections.emptySet();
        }
        return Optional.ofNullable(permissionMapper.selectBatchIds(permissionIdList))
                .map(permissionList ->  permissionList.stream()
                        .map(Permission::getMethod)
                        .collect(Collectors.toSet())
                )
                .orElse(Collections.emptySet());
    }

}
