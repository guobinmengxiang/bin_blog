package com.bin.blog.service;

import com.bin.blog.entity.Link;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface LinkService {
	/**
	 * @param 得到分页数据
	 * @return
	 */
	PageResult getBlogLinkPage(PageQueryUtil pageUtil);
	/**
	 * @param 新增友链
	 * @return
	 */
	Boolean saveLink(Link link);
	/**
	 * @param 删除友链
	 * @return
	 */
	Boolean deleteBatch(Integer [] ids);
	 /**
	 * @param 根据id查询友链
	 * @return
	 */
	Link selectById(Integer id);
	    /**
	     * @param 修改友链
	     * @return
	     */
	    Boolean updateLink(Link tempLink);
}
