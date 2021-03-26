package com.jt.projects.results;

import com.jt.projects.validation.ValidationExceptionEnum;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class CompanyResult<T> extends BaseTO {

    /**
     * 存储返回的数据
     */
    private T returnData;
    /**
     * 请求是否成功的标记
     */
    private Boolean success;

    /**
     * 私有空参构造器 避免外部直接new空参对象返回
     */
    private CompanyResult(){}

    public CompanyResult(Boolean success,T data){
        this.success = success;
        if (data == null){
            // 当data为空时 默认将返回的数据 设置为成功的描述
            this.returnData = (T) ValidationExceptionEnum.SUCCESS.getDesc();
            if (!success){
                this.returnData = (T) ValidationExceptionEnum.ERROR_PARAM.getDesc();
            }
        }
        // 当返回数据不为空时
        this.returnData = data;
    }

    /**
     * 静态方法 不能使用泛型 类型参数T
     * @param data 返回的数据
     * @param <R>
     * @return
     */
    public static <R> CompanyResult<R> ok(R data){
        return new CompanyResult<>(true,data);
    }

    /**
     * 静态方法 不能使用泛型 类型参数T
     * @param data 返回的数据
     * @param <R>
     * @return
     */
    public static <R> CompanyResult<R> failed(R data){
        return new CompanyResult<>(false,data);
    }

    /**
     * 支持Map增加值
     * @param map
     */
    public void supplyMap(Map<String,Object> map){
        if (CollectionUtils.isEmpty(map)){
            map = new HashMap<>();
        }
        if (this.returnData instanceof  Map){
            map = (Map<String, Object>) this.returnData;
        }
        map.putAll(map);
    }
}
