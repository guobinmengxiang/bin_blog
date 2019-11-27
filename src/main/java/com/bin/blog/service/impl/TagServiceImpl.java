package com.bin.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bin.blog.dao.TagMapper;
import com.bin.blog.dao.TagRelationMapper;
import com.bin.blog.entity.BlogTagCount;
import com.bin.blog.entity.Tag;
import com.bin.blog.service.TagService;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;
@Service
public class TagServiceImpl implements TagService {

	 @Autowired
	    private TagMapper tagMapper;
	    @Autowired
	    private TagRelationMapper relationMapper;

	    @Override
	    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
	        List<Tag> tags = tagMapper.findTagList(pageUtil);
	        int total = tagMapper.getTotalTags(pageUtil);
	        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
	        return pageResult;
	    }

	    @Override
	    public Boolean saveTag(String tagName) {
	        Tag temp = tagMapper.selectByTagName(tagName);
	        if (temp == null) {
	        	Tag blogTag = new Tag();
	            blogTag.setTagName(tagName);
	            return tagMapper.insertSelective(blogTag) > 0;
	        }
	        return false;
	    }

	    @Override
	    public Boolean deleteBatch(Integer[] ids) {
	        //已存在关联关系不删除
	        List<Long> relations = relationMapper.selectDistinctTagIds(ids);
	        if (!CollectionUtils.isEmpty(relations)) {
	            return false;
	        }
	        //删除tag
	        return tagMapper.deleteBatch(ids) > 0;
	    }

	    @Override
	    public List<BlogTagCount> getBlogTagCountForIndex() {
	        return tagMapper.getTagCount();
	    }
	}