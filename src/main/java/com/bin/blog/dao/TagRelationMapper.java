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

	    List<Long> selectDistinctTagIds(Integer[] tagIds);

	    int updateByPrimaryKeySelective(TagRelation record);

	    int updateByPrimaryKey(TagRelation record);
	    int batchInsert(@Param("relationList") List<TagRelation> blogTagRelationList);

	}