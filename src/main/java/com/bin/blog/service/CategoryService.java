package com.bin.blog.service;
import java.util.List;

import com.bin.blog.entity.Category;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

public interface CategoryService {

    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName, String categoryIcon);
    /**
     * 修改分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<Category> getAllCategories();//

    Category selectById(Integer id);
}
