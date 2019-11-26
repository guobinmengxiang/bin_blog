package com.bin.blog.dao;

import java.util.List;

import com.bin.blog.entity.Link;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface LinkMapper {
	 int deleteByPrimaryKey(Integer linkId);

	    int insert(Link record);

	    int insertSelective(Link record);

	    Link selectByPrimaryKey(Integer linkId);

	    int updateByPrimaryKeySelective(Link record);

	    int updateByPrimaryKey(Link record);

	    List<Link> findLinkList(PageQueryUtil pageUtil);

	    int getTotalLinks(PageQueryUtil pageUtil);

	    int deleteBatch(Integer[] ids);
}