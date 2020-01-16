package com.bin.blog.service;

import java.util.List;
import java.util.Map;

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
	    /**
	     * 返回友链页面所需的所有数据
	     *
	     * @return
	     */
	    Map<Byte, List<Link>> getLinksForLinkPage();
	    /**
	     * @return 友链总数
	     */
	    int getTotalLinks();
}
