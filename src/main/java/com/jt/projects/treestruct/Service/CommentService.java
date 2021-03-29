package com.jt.projects.treestruct.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.projects.treestruct.entity.Comment;
import com.jt.projects.treestruct.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public List<Comment> getAllCommentsByTargetId(Integer targetId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper();
        queryWrapper.eq("target_id",targetId);
        queryWrapper.orderByAsc("id");
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        return commentList;
    }
}