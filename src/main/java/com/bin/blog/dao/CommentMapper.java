package com.bin.blog.dao;

import org.springframework.stereotype.Component;

import com.bin.blog.entity.Comment;

import java.util.List;
import java.util.Map;

@Component
public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findBlogCommentList(Map map);

    int getTotalBlogComments(Map map);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}