package com.jt.projects.results;

import com.jt.projects.validation.ValidationExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiResultDTO<T extends Serializable> extends PageQueryDTO{
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public static <R extends Serializable> ApiResultDTO<R> buildSuccess(R data,String message){
        ApiResultDTO<R> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setCode(200);
        apiResultDTO.setSuccess(true);
        apiResultDTO.setMessage(message);
        apiResultDTO.setData(data);
        return apiResultDTO;
    }

    public static <R extends Serializable> ApiResultDTO<R> buildSuccess(R data){
        return buildSuccess(data,null);
    }


    public static <R extends Serializable> ApiResultDTO<R> buildFailed(Integer code,String message){
        ApiResultDTO<R> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setSuccess(false);
        apiResultDTO.setMessage(message);
        apiResultDTO.setCode(code);
        return apiResultDTO;
    }

    public static <R extends Serializable> ApiResultDTO<R> buildFailed(ValidationExceptionEnum validationExceptionEnum){
        return buildFailed(validationExceptionEnum.getCode(),validationExceptionEnum.getDesc());
    }
}
