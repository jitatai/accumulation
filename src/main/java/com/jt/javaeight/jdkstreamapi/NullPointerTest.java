package com.jt.javaeight.jdkstreamapi;

import lombok.*;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class NullPointerTest {
    /**
     * 需求：根据用户名查找该用户所在的部门名称
     *
     * @param args
     */
    public static void main(String[] args) {
        String departmentNameOfUser = getDepartmentNameOfUser("test");
        System.out.println(departmentNameOfUser);
    }

    /**
     * 假设这是A-Service的服务
     * 这一步很烦！！！
     *
     * @param username
     * @return
     */
    public static String getDepartmentNameOfUser(String username) {
        ResultTO<User> resultTO = getUserByName(username);
        if (resultTO != null) {
            User user = resultTO.getData();
            if (user != null) {
                Department department = user.getDepartment();
                if (department != null) {
                    return department.getName();
                }
            }
        }
        return "未知部门";
    }

    /**
     * 假设这是B-Service的服务（不用关注具体逻辑，就是随机模拟返回值，可能为null）
     *
     * @param username
     * @return
     */
    public static ResultTO<User> getUserByName(String username) {
        if (username == null || "".equals(username)) {
            return null;
        }

        Department department;
        User user;

        if (ThreadLocalRandom.current().nextBoolean()) {
            department = new Department("总裁办", 10086);
        } else {
            department = null;
        }
        if (ThreadLocalRandom.current().nextBoolean()) {
            user = new User("周董", 18, department);
            user.setDepartment(department);
        } else {
            user = null;
        }

        return ResultTO.buildSuccess(user);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User {
        private String name;
        private Integer age;
        private Department department;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Department {
        private String name;
        private Integer code;
    }
    
    @Getter
    @Setter
    static class ResultTO<T> implements Serializable {

        private Boolean success;
        private String message;
        private T data;

        public static <T> ResultTO<T> buildSuccess(T data) {
            ResultTO<T> result = new ResultTO<>();
            result.setSuccess(true);
            result.setMessage("success");
            result.setData(data);
            return result;
        }

        public static <T> ResultTO<T> buildFailed(String message) {
            ResultTO<T> result = new ResultTO<>();
            result.setSuccess(false);
            result.setMessage(message);
            result.setData(null);
            return result;
        }
    }
}