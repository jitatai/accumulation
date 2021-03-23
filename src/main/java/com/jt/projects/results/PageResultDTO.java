package com.jt.projects.results;

import com.github.pagehelper.Page;
import com.jt.projects.validation.ValidationExceptionEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResultDTO<T> extends PageQueryDTO {

    private Integer code;
    private String message;
    private List<T> data;
    // 新增和分页相关的三个字段


    private PageResultDTO(List<T> data, Integer page, Integer pageSize, Long total) {
        this.code = ValidationExceptionEnum.SUCCESS.getCode();
        this.message = ValidationExceptionEnum.SUCCESS.getDesc();
        this.data = data;
        this.setPage(page);
        this.setPageSize(pageSize);
        this.setTotal(total);
    }

    /**
     * 成功实体，传入一个page对象
     *
     * @param page
     */
    public static <T> PageResultDTO<T> success(Page<T> page) {
        if (page != null) {
            return new PageResultDTO<>(page.getResult(), page.getPageNum(), page.getPageSize(), page.getTotal());
        } else {
            return new PageResultDTO<>(new ArrayList<T>(), 0, 1, 10L);
        }
    }
}