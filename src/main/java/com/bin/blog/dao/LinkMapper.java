package com.bin.blog.dao;

import java.util.List;

import com.bin.blog.entity.Link;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface LinkMapper {
	 int deleteByPrimaryKey(Integer linkId);

	    int insert(Link record);

	    /**
	     * @param 新增友链
	     * @return
	     */
	    int insertSelective(Link record);

	    /**
	     * @param 根据id查询友链
	     * @return
	     */
	    Link selectByPrimaryKey(Integer linkId);

	    /**
	     * @param 修改友链
	     * @return
	     */
	    int updateByPrimaryKeySelective(Link record);

	    int updateByPrimaryKey(Link record);

	    /**
	     * @param 得到所有的友链
	     * @return
	     */
	    List<Link> findLinkList(PageQueryUtil pageUtil);

	    /**
	     * @param 总数
	     * @return
	     */
	    int getTotalLinks(PageQueryUtil pageUtil);

	    /**
	     * @param 删除友链
	     * @return
	     */
	    int deleteBatch(Integer[] ids);
}