package com.jt.projects.authorizationrbac.intercepter;

import com.jt.projects.authorizationrbac.annotation.LoginRequired;
import com.jt.projects.authorizationrbac.annotation.PermissionRequired;
import com.jt.projects.authorizationrbac.constant.WebConstant;
import com.jt.projects.authorizationrbac.entity.rbac.User;
import com.jt.projects.authorizationrbac.enums.ExceptionCodeEnum;
import com.jt.projects.authorizationrbac.exception.BizException;
import com.jt.web.request.threads.ThreadLocalUtilV4;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/30 14:56
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Resource
    HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        session = request.getSession();
        // 不拦截跨域相关
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 如果方法上没有加@LoginRequired，无需登录，直接放行
        if (isLoginFree(handler)) {
            return true;
        }
        // 如果需要登录、做登录校验
        User user = handlerLogin(request,response);
        ThreadLocalUtilV4.put(WebConstant.USER_INFO,user);

        // 校验权限
        checkPermission(user,handler);
        return super.preHandle(request,response,handler);
    }

    private void checkPermission(User user, Object handler) {
        if (isPermissionFree(handler)){
            return;
        }
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Class<?> controllerClass = handlerMethod.getBeanType();
            String methodName = controllerClass.getName()+ "#" + method.getName();

            Set<String> permissions = (Set<String>) session.getAttribute(WebConstant.USER_PERMISSIONS);
            if (permissions.contains(methodName)){
                return;
            }
            throw new BizException(ExceptionCodeEnum.PERMISSION_DENY);
        }
    }

    private boolean isPermissionFree(Object handler) {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBeanType();
            PermissionRequired controllerAnnotation = AnnotationUtils.findAnnotation(controllerClass, PermissionRequired.class);
            PermissionRequired methodAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getMethod(), PermissionRequired.class);
            return controllerAnnotation == null && methodAnnotation == null;
        }
        return true;
    }

    private User handlerLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(WebConstant.CURRENT_USER_IN_SESSION);
        if (user == null){
            throw new BizException(ExceptionCodeEnum.NEED_LOGIN);
        }
        return user;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtilV4.remove(WebConstant.CURRENT_USER_IN_SESSION);
        super.afterCompletion(request,response,handler,ex);
    }

    /**
     * 接口是否免登录
     *
     * @param handler
     * @return
     */
    private boolean isLoginFree(Object handler) {
        // 判断是否支持免登录
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // AnnotationUtils是Spring提供的注解工具类，还有很多其他便利的方法
            LoginRequired loginRequiredAnnotation = AnnotationUtils.getAnnotation(method, LoginRequired.class);
            LoginRequired annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), LoginRequired.class);
            // 没有加@LoginRequired，不需要登录
            return loginRequiredAnnotation == null && annotation == null;
        }

        return true;
    }
}
