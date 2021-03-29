package com.jt.projects.treestruct.Service;

import com.jt.projects.treestruct.entity.Superman;

import java.util.List;

/**
 * @author Administrator
 */
public interface SupermanService {
    /**
     * 获取所有的SuperMan
     * @return
     */
    public List<Superman> getAllSuperman();

    List<Superman> getAllSupermanByFor();

    List<Superman> mapForAll();
}
