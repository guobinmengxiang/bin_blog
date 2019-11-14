package com.bin.blog.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bin.blog.entity.Tag;
import com.bin.blog.util.PageQueryUtil;
@Component
public interface TagMapper {
	 int deleteByPrimaryKey(Integer tagId);

	    int insert(Tag record);

	    int insertSelective(Tag record);

	    Tag selectByPrimaryKey(Integer tagId);

	    Tag selectByTagName(String tagName);

	    int updateByPrimaryKeySelective(Tag record);

	    int updateByPrimaryKey(Tag record);

	    List<Tag> findTagList(PageQueryUtil pageUtil);

	    int getTotalTags(PageQueryUtil pageUtil);

	    int deleteBatch(Integer[] ids);
	    int batchInsertBlogTag(List<Tag> tagList);
}