package com.bin.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bin.blog.entity.Category;
import com.bin.blog.util.PageQueryUtil;
@Component
public interface CategoryMapper {
	// int deleteByPrimaryKey(Integer categoryId);

	 //   int insert(Category record);
         //新增分类
	    int insertSelective(Category record);

	    Category selectByPrimaryKey(Integer categoryId);
        //查询是否有此分类
	    Category selectByCategoryName(String categoryName);
        //修改分类名称
	    int updateByPrimaryKeySelective(Category record);

	   // int updateByPrimaryKey(Category record);
        //查询分页信息
	    List<Category> findCategoryList(PageQueryUtil pageUtil);

	    List<Category> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);
        //获取总数
	    int getTotalCategories(PageQueryUtil pageUtil);
        //删除
	    int deleteBatch(Integer[] ids);
}