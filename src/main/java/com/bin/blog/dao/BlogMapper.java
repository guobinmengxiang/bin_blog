package com.bin.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bin.blog.entity.Blog;
import com.bin.blog.entity.Category;
import com.bin.blog.util.PageQueryUtil;

public interface BlogMapper {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
    List<Blog> findBlogList(PageQueryUtil pageUtil);
    int getTotalBlogs(PageQueryUtil pageUtil);
    int deleteBatch(Integer[] ids);
    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

}