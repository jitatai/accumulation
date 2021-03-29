package com.jt.accumulation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.projects.treestruct.Service.CommentService;
import com.jt.projects.treestruct.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testComment() throws JsonProcessingException {
        // =========查出targetId下所有评论（一篇文章下的所有评论）==========
        List<Comment> commentList = commentService.getAllCommentsByTargetId(10086);

        // =========对平铺数据进行嵌套整理==========
        // 最终结果
        List<Comment> result = new ArrayList<>();

        // list转map，建立索引
        Map<Integer, Comment> commentMap = commentList.stream()
                .collect(Collectors.toMap(Comment::getId, v -> v, (v1, v2) -> v2));

        // 嵌套数据
        commentList.stream().forEach(comment -> {
            /**
             * 归纳评论：对文章的评论是第一级，对文章的评论的评论是第二级，把第二级评论塞到对应的第一级评论下，作为replies
             *
             * 《静夜思》
             * 床前明月光
             * 疑似地上霜
             * -----------------------
             * a：第一级评论1
             *   a 回复 b：第二级评论1
             *   b 回复 a：第二级评论2
             *
             * c：第一级评论2
             *   c 回复 d：第二级评论3
             *   d 回复 c：第二级评论4
             */
            if (comment.getPid() == 0) {
                // 一级评论
                result.add(comment);
            } else{
                // 二级评论，那么肯定有一级评论且firstComment一定不为null
                Comment firstComment = commentMap.get(comment.getPid());
                // 把二级评论塞到一级评论下
                firstComment.getReplies().add(comment);
            }
        });
        prettyPrint(result);
    }

    private void prettyPrint(List<Comment> commentList) throws JsonProcessingException {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commentList));
    }
}