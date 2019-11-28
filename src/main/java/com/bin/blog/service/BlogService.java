package com.bin.blog.service;

import java.util.List;

import com.bin.blog.controller.vo.SimpleBlogListVO;
import com.bin.blog.entity.Blog;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface BlogService {
String save(Blog blog);
Blog getBlogById(Long blogid);
String updateBlog(Blog blog);
PageResult getBlogsPage(PageQueryUtil pageUtil);
Boolean deleteBatch(Integer[] ids);
/**
 * 首页侧边栏数据列表
 * 0-点击最多 1-最新发布
 *
 * @param type
 * @return
 */
List<SimpleBlogListVO> getBlogListForIndexPage(int type);

/**
 * 获取首页文章列表
 *
 * @param page
 * @return
 */
PageResult getBlogsForIndexPage(int page);
PageResult getBlogsPageBySearch(String keyword, int page);

/**
 * 根据分类获取文章列表
 *
 * @param categoryId
 * @param page
 * @return
 */
PageResult getBlogsPageByCategory(String categoryId, int page);


/**
 * 根据标签获取文章列表
 *
 * @param tagName
 * @param page
 * @return
 */
PageResult getBlogsPageByTag(String tagName, int page);
}