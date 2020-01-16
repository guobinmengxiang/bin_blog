package com.bin.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bin.blog.entity.Blog;
import com.bin.blog.entity.Category;
import com.bin.blog.util.PageQueryUtil;

public interface BlogMapper {
    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    /**
     * @param 新增博客
     * @return
     */
    int insertSelective(Blog record);

    /**
     * @param 根据博客id查询博客内容用于展示
     * @return
     */
    Blog selectByPrimaryKey(Long blogId);

    /**
     * @param 修改博客信息
     * @return
     */
    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
    List<Blog> findBlogList(PageQueryUtil pageUtil);
    int getTotalBlogs(PageQueryUtil pageUtil);
    /**
     * @param 删除博客信息
     * @return
     */
    int deleteBatch(Integer[] ids);
    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);
    List<Blog> getBlogsPageByTagId(PageQueryUtil pageUtil);
    int getTotalBlogsByTagId(PageQueryUtil pageUtil);
    Blog selectBySubUrl(String subUrl);

}