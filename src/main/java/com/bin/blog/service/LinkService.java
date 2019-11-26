package com.bin.blog.service;

import com.bin.blog.entity.Link;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface LinkService {
	PageResult getBlogLinkPage(PageQueryUtil pageUtil);
	Boolean saveLink(Link link);
	Boolean deleteBatch(Integer [] ids);
	 Link selectById(Integer id);
	    Boolean updateLink(Link tempLink);
}
