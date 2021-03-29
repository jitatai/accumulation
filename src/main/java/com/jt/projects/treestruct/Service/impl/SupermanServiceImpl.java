package com.jt.projects.treestruct.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.projects.treestruct.Service.SupermanService;
import com.jt.projects.treestruct.entity.Superman;
import com.jt.projects.treestruct.mapper.SupermanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class SupermanServiceImpl implements SupermanService {
    @Resource
    private SupermanMapper supermanMapper;

    @Override
    public List<Superman> getAllSuperman() {
        List<Superman> list = new ArrayList<>();
        /**
         * 查出所有数据
         */
        List<Superman> supermanList = supermanMapper.selectList(new QueryWrapper<>());

        // 递归设置值
        supermanList.forEach(superman -> {
            superman.setIsParent(false);
            if (superman.getPid() == 0){
                List<Superman> children = queryMyChildren(superman,supermanList);
                superman.setChildren(children);
                list.add(superman);
            }
        });

        return list;
    }

    @Override
    public List<Superman> getAllSupermanByFor() {
        List<Superman> dataList = new ArrayList<>();
        List<Superman> supermanList = supermanMapper.selectList(new QueryWrapper<>());
        supermanList.forEach(left->{
            if (left.getPid() == 0){
                dataList.add(left);
            }
            supermanList.forEach(right->{
                // 左边是父亲 右边是儿子
                if (left.getId().equals(right.getPid())){
                    left.getChildren().add(right);
                }
            });
        });
        return dataList;
    }

    @Override
    public List<Superman> mapForAll() {
        List<Superman> dataList = new ArrayList<>();
        List<Superman> supermanList = supermanMapper.selectList(new QueryWrapper<>());
        Map<Integer, Superman> collect = supermanList.stream().collect(Collectors.toMap(Superman::getId, v -> v));
        supermanList.forEach(superman -> {
            if (superman.getPid() == 0){
                dataList.add(superman);
            }
            Superman superman1 = collect.get(superman.getPid());
            if (null != superman1){
                superman1.getChildren().add(superman);
            }
        });
        return dataList;
    }

    private List<Superman> queryMyChildren(Superman superman, List<Superman> supermanList) {
        List<Superman> children = new ArrayList<>();
        supermanList.forEach(man -> {
            if (man.getPid().equals(superman.getId())){
                children.add(man);
                superman.setIsParent(true);
            }
        });

        children.forEach(child ->{
            List<Superman> grandChild = queryMyChildren(child, supermanList);
            child.setChildren(grandChild);
        });

        return children;
    }
}
