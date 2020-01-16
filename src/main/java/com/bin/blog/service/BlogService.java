package com.bin.blog.service;

import java.util.List;

import com.bin.blog.controller.vo.BlogDetailVO;
import com.bin.blog.controller.vo.SimpleBlogListVO;
import com.bin.blog.entity.Blog;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface BlogService {
	
/**
 * @param add blog
 * @return
 */
String save(Blog blog);
/**
 * @param 根据博客id得到博客内容
 * @return
 */
Blog getBlogById(Long blogid);
/**
 * @param 修改博客信息
 * @return
 */
String updateBlog(Blog blog);
PageResult getBlogsPage(PageQueryUtil pageUtil);
/**
 * @param 删除博客信息
 * @return
 */
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
/**
 * 文章详情获取
 *
 * @param blogId
 * @return
 */
BlogDetailVO getBlogDetail(Long blogId);
BlogDetailVO getBlogDetailBySubUrl(String subUrl);
/**
 * @return 博客总数
 */
int getTotalBlogs();
}