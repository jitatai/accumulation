package com.jt.projects.aop.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.controllerenum.pojo.UserDTO;
import com.jt.projects.aop.annations.UserLog;
import com.jt.projects.aop.entity.dto.UserLogDTO;
import com.jt.projects.aop.service.UserLogService;
import com.jt.web.request.threads.ThreadLocalUtilV4;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/3/26 13:36
 */
@Aspect
public class ApiUserLogAspect {
    @Autowired
    UserLogService userLogService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    HttpServletRequest request;
    /**
     * 设置切点表达式 只针对使用了@UserLog
     */
    @Pointcut("@annotation(com.jt.projects.aop.annations.UserLog)")
    public void pointCut(){}

    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinpoint){
        saveSysUserLog((ProceedingJoinPoint) joinpoint);
    }

    private void saveSysUserLog(ProceedingJoinPoint joinpoint) {
        UserDTO userDTO = getUserDTO();
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        UserLog annotation = method.getAnnotation(UserLog.class);
        if (annotation == null){
            return;
        }
        // 当方法中有UserLog注解时 记录进数据库 收集相关信息并保存
        UserLogDTO userLogDTO = buildUserLogDTO(userDTO,joinpoint,annotation);

        //存入数据库
        userLogService.addSysLog(userLogDTO);
    }

    private UserLogDTO buildUserLogDTO(UserDTO userDTO, ProceedingJoinPoint joinpoint, UserLog annotation) {
        UserLogDTO userLogDTO = new UserLogDTO();
        userLogDTO.setOperatorId(userDTO.getId());
        userLogDTO.setType(annotation.operationType().getValue());
        userLogDTO.setTitle(annotation.title());
        userLogDTO.setModuleCode(annotation.module().getModuleCode());
        userLogDTO.setContent(getContent(joinpoint));
        userLogDTO.setOperateTime(new Date());
        return userLogDTO;
    }

    private String getContent(ProceedingJoinPoint joinpoint) {
        if ("GET".equals(request.getMethod())){
            return request.getQueryString();
        }
        Object[] objects = Arrays.stream(joinpoint.getArgs()).filter(arg -> !(arg instanceof ServletRequest ||
                arg instanceof ServletResponse ||
                arg instanceof MultipartFile)).toArray();
        return objectMapper.valueToTree(objects).toPrettyString();
    }

    private UserDTO getUserDTO() {
        UserDTO userDTO = (UserDTO)ThreadLocalUtilV4.get("userDTO");
        return userDTO;
    }
}
