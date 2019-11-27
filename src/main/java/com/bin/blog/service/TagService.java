package com.bin.blog.service;

import java.util.List;

import com.bin.blog.entity.BlogTagCount;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface TagService {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);
    List<BlogTagCount> getBlogTagCountForIndex();
}
