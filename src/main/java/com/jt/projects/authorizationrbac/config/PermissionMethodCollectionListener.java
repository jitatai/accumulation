package com.jt.projects.authorizationrbac.config;

import com.jt.projects.authorizationrbac.annotation.PermissionRequired;
import com.jt.projects.authorizationrbac.entity.rbac.Permission;
import com.jt.projects.authorizationrbac.mapper.rbac.PermissionMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/1 11:08
 * ApplicationContextAware 在自动注入时获取ApplicationContext
 * ApplicationListener
 */
@Component
public class PermissionMethodCollectionListener implements  ApplicationContextAware {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获得数据库中已经存在的权限permission
        List<Permission> permissions = permissionMapper.selectList(null);
        Set<String> permissionSetForDB = Optional.ofNullable(permissions).map(list ->
                list.stream().map(Permission::getMethod).collect(Collectors.toSet())).orElse(new HashSet<>());

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Controller.class);

        beanMap.values().forEach(bean->{
            Class<?> beanClass = bean.getClass();
            Predicate<Method> filter = AnnotationUtils.findAnnotation(beanClass, PermissionRequired.class) != null ?
                    this::isApiMethod : this::isPermissionMethod;

            Set<String> permissionMethods = Arrays
                    .stream(beanClass.getDeclaredMethods())
                    .filter(filter)
                    .map(method -> beanClass.getName() + "#" + method.getName())
                    .collect(Collectors.toSet());

            permissionMethods.forEach(permissionMethod->{
                if (!permissionSetForDB.contains(permissionMethod)){
                    savePermission(permissionMethod);
                }
            });

        });
    }

    private void savePermission(String permissionMethod) {
        Permission permission = new Permission();
        permission.setName("");
        permission.setModule("");
        permission.setMethod(permissionMethod);
        permissionMapper.insert(permission);
    }

    private boolean isPermissionMethod(Method method) {
        return AnnotationUtils.findAnnotation(method, RequestMapping.class) != null &&
                AnnotationUtils.findAnnotation(method,PermissionRequired.class) !=null;
    }

    private boolean isApiMethod(Method method) {
        return AnnotationUtils.findAnnotation(method, RequestMapping.class) != null;
    }


}
