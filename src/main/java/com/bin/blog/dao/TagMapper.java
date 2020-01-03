package com.bin.blog.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bin.blog.entity.BlogTagCount;
import com.bin.blog.entity.Tag;
import com.bin.blog.util.PageQueryUtil;
@Component
public interface TagMapper {
	 int deleteByPrimaryKey(Integer tagId);

	    int insert(Tag record);

	    /**
	     * @param 插入标签
	     * @return
	     */
	    int insertSelective(Tag record);

	  //  Tag selectByPrimaryKey(Integer tagId);

	    /**
	     * @param 查询库里是否有这个标签
	     * @return
	     */
	    Tag selectByTagName(String tagName);

	  //  int updateByPrimaryKeySelective(Tag record);

	  //  int updateByPrimaryKey(Tag record);

	    /**
	     * @param 查询分页数据
	     * @return
	     */
	    List<Tag> findTagList(PageQueryUtil pageUtil);

	    /**
	     * @param 获取标签总量
	     * @return
	     */
	    int getTotalTags(PageQueryUtil pageUtil);

	    /**
	     * @param 删除
	     * @return
	     */
	    int deleteBatch(Integer[] ids);
	   /**
	 * @param 博客标签表中新增数据
	 * @return
	 */
	int batchInsertBlogTag(List<Tag> tagList);
	   List<BlogTagCount> getTagCount();

}