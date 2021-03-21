package com.jt.projects.validation;

public class ValidationUtilsV1 {
    private ValidationUtilsV1(){}

    /**
     * 检验方法只返回true/false
     * @param id
     * @return
     */
    public static final Boolean isNotId(Long id){
        if (id == null){
            return true;
        }
        return id < 0;
    }
    /**
     * 调用者根据返回值自行处理
     */
    public void method(){
        Long id = -1L;
        ValidationUtilsV1.isNotId(id);
    }

}
