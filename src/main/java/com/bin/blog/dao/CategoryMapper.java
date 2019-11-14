package com.bin.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bin.blog.entity.Category;
import com.bin.blog.util.PageQueryUtil;
@Component
public interface CategoryMapper {
	 int deleteByPrimaryKey(Integer categoryId);

	    int insert(Category record);

	    int insertSelective(Category record);

	    Category selectByPrimaryKey(Integer categoryId);

	    Category selectByCategoryName(String categoryName);

	    int updateByPrimaryKeySelective(Category record);

	    int updateByPrimaryKey(Category record);

	    List<Category> findCategoryList(PageQueryUtil pageUtil);

	    List<Category> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

	    int getTotalCategories(PageQueryUtil pageUtil);

	    int deleteBatch(Integer[] ids);
}