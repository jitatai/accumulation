package com.jt.accumulation.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table("user")
public class User {
    private String name;
    private Integer age;
}