package com.bin.blog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Override
    public Map<Byte, List<Link>> getLinksForLinkPage() {
        //获取所有链接数据
        List<Link> links = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            Map<Byte, List<Link>> linksMap = links.stream().collect(Collectors.groupingBy(Link::getLinkType));
            return linksMap;
        }
        return null;
    }
    @Override
    public int getTotalLinks() {
        return blogLinkMapper.getTotalLinks(null);
    }


}
