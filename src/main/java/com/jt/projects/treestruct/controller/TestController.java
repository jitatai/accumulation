package com.jt.projects.treestruct.controller;

import com.jt.projects.treestruct.entity.JavaBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @RequestMapping("testList")
    public List getList() {
        // 定义一个List，用来存储最终结果
        ArrayList<JavaBean> superMen = new ArrayList<>();

        //------------------------七龙珠（从下往上看会好理解些，开枝散叶）------------------------
        // 孙悟饭的儿子们：悟饭儿子1、悟饭儿子2
        List<JavaBean> grandChildren1 = new ArrayList<>();
        grandChildren1.add(new JavaBean("悟饭儿子1", false, false, null));
        grandChildren1.add(new JavaBean("悟饭儿子2", false, false, null));

        // 孙悟天的儿子们：悟天儿子1、悟天儿子2
        List<JavaBean> grandChildren2 = new ArrayList<>();
        grandChildren2.add(new JavaBean("悟天儿子1", false, false, null));
        grandChildren2.add(new JavaBean("悟天儿子2", false, false, null));

        // 孙悟空的儿子：悟饭、悟天
        List<JavaBean> children = new ArrayList<>();
        children.add(new JavaBean("悟饭", false, true, grandChildren1));
        children.add(new JavaBean("悟天", false, true, grandChildren2));

        // 孙悟空本人
        JavaBean wukong = new JavaBean("悟空", true, true, children);

        //------------------------火影忍者（从下往上看会好理解些，开枝散叶）------------------------
        // 鸣人的徒弟们：博人1、博人2
        List<JavaBean> grandChildren3 = new ArrayList<>();
        grandChildren3.add(new JavaBean("博人1", false, false, null));
        grandChildren3.add(new JavaBean("博人2", false, false, null));

        // 佐助的徒弟们：佐良娜1、佐良娜2
        List<JavaBean> grandChildren4 = new ArrayList<>();
        grandChildren4.add(new JavaBean("佐良娜1", false, false, null));
        grandChildren4.add(new JavaBean("佐良娜2", false, false, null));

        // 卡卡西的徒弟们：鸣人、佐助
        List<JavaBean> children2 = new ArrayList<>();
        children2.add(new JavaBean("鸣人", false, true, grandChildren3));
        children2.add(new JavaBean("佐助", false, true, grandChildren4));

        // 卡卡西本人
        JavaBean kakaxi = new JavaBean("卡卡西", true, true, children2);

        //------------------------处理结果------------------------
        // 只把孙悟空和卡卡西加入List
        superMen.add(wukong);
        superMen.add(kakaxi);

        return superMen;
    }
}