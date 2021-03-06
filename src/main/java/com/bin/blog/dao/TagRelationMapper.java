package com.bin.blog.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.bin.blog.entity.TagRelation;
@Component
public interface TagRelationMapper {
	 int deleteByPrimaryKey(Long relationId);

	    int insert(TagRelation record);

	    int insertSelective(TagRelation record);

	    TagRelation selectByPrimaryKey(Long relationId);

	    /**
	     * @param 判断此标签id是否有关联的文章
	     * @return
	     */
	    List<Long> selectDistinctTagIds(Integer[] tagIds);

	    int updateByPrimaryKeySelective(TagRelation record);

	    int updateByPrimaryKey(TagRelation record);
	    /**
	     * @param标签关系表
	     * @return
	     */
	    int batchInsert(@Param("relationList") List<TagRelation> blogTagRelationList);
	    /**
	     * @param 删除对应的关联信息
	     * @return
	     */
	    int deleteByBlogId(Long id);

	}