package com.jt.javaeight.optional;

import lombok.*;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/23 9:51
 */
public class NullPointerTest {
    public static void main(String[] args) {
        String departmentNameOfUser = getDepartmentNameOfUser("test");
        System.out.println(departmentNameOfUser);

        // 自定义NullableWrapper
        Result<User> test = getUserByName("test");
        String departmentName = NullableWrapper.ofNullable(test)
                .map(Result::getData).
                        map(User::getDepartment).
                        map(Department::getName).orElse("未查到部门名称");

        // Optional
        Optional.ofNullable(getUserByName("test"))
                .map(Result::getData)
                .map(User::getDepartment)
                .map(Department::getName);
        System.out.println(departmentName);
    }

    private static String getDepartmentNameOfUser(String username) {
        Result<User> userResult = getUserByName(username);
        if (userResult == null){
            return "Result is null";
        }

        User user = userResult.getData();
        if (user == null){
            return "User is null";
        }

        Department department = user.getDepartment();
        if (department == null){
            return "department is null";
        }

        return department.getName();
    }

    private static Result<User> getUserByName(String username) {
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

        return Result.buildSuccess(user);
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private Integer age;
    private Department department;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Department {
    private String name;
    private Integer code;
}

@Getter
@Setter
@AllArgsConstructor
class Result<T>{
    private Boolean success;
    private String message;
    private T data;

    public static <T> Result<T> buildSuccess(T data){
        return new Result<>(true,"success",data);
    }

    public static <T> Result<T> buildFailer(T data){
        return new Result<>(false,"failer",data);
    }
}