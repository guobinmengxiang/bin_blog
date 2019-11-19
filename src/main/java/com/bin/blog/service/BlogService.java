package com.bin.blog.service;

import com.bin.blog.entity.Blog;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface BlogService {
String save(Blog blog);
Blog getBlogById(Long blogid);
String updateBlog(Blog blog);
PageResult getBlogsPage(PageQueryUtil pageUtil);
Boolean deleteBatch(Integer[] ids);
}