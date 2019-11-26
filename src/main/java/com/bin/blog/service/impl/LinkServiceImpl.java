package com.bin.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bin.blog.dao.LinkMapper;
import com.bin.blog.entity.Link;
import com.bin.blog.service.LinkService;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;
@Service
public class LinkServiceImpl implements LinkService {
	 @Autowired
private LinkMapper blogLinkMapper;
 	@Override
	  public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<Link> links = blogLinkMapper.findLinkList(pageUtil);
        int total = blogLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
	@Override
	   public Boolean saveLink(Link link) {
        return blogLinkMapper.insertSelective(link) > 0;
    }
	@Override
	public Boolean deleteBatch(Integer[] ids) {
		// TODO Auto-generated method stub
		 return blogLinkMapper.deleteBatch(ids) > 0;
	}
	@Override
    public Link selectById(Integer id) {
        return blogLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(Link tempLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(tempLink) > 0;
    }

}
