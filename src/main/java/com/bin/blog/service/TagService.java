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

    /**
     * @param 查询库里是否有这个标签
     * @return
     */
    Boolean saveTag(String tagName);

    /**
     * @param 删除标签
     * @return
     */
    Boolean deleteBatch(Integer[] ids);
    List<BlogTagCount> getBlogTagCountForIndex();
    /**
     * @return 标签总数
     */
    int getTotalTags();
}
