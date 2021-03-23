package com.jt.projects.results;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResultTO<T extends Serializable> extends BaseTO {

    private static final long serialVersionUID = 5962947140449215602L;
    /**
     * 是否查询成功
     */
    private Boolean success;

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 列表结果
     */
    private List<T> itemList;
    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage;

    public static <K extends Serializable> PageResultTO<K> buildFailed(String errorCode, String message) {
        PageResultTO<K> pageResult = new PageResultTO<>();
        pageResult.setSuccess(false);
        pageResult.setErrorCode(errorCode);
        pageResult.setMessage(message);
        return pageResult;
    }

    public static <K extends Serializable> PageResultTO<K> buildSuccess(List<K> itemList, Integer currentPage,
                                                                        Boolean hasNextPage) {
        PageResultTO<K> pageResult = new PageResultTO<>();
        pageResult.setSuccess(true);
        pageResult.setItemList(itemList);
        pageResult.setCurrentPage(currentPage);
        pageResult.setHasNextPage(hasNextPage);
        return pageResult;
    }

    public static <K extends Serializable> PageResultTO<K> buildSuccess(List<K> itemList) {
        PageResultTO<K> pageResult = new PageResultTO<>();
        pageResult.setSuccess(true);
        pageResult.setItemList(itemList);
        pageResult.setCurrentPage(1);
        pageResult.setHasNextPage(Boolean.FALSE);
        return pageResult;
    }
}