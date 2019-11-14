package com.bin.blog.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bin.blog.dao.CategoryMapper;
import com.bin.blog.entity.Category;
import com.bin.blog.service.CategoryService;
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<Category> categoryList = categoryMapper.findCategoryList(pageUtil);
        int total = categoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return categoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        Category temp = categoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            Category blogCategory = new Category();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return categoryMapper.insertSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        Category blogCategory = categoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategory == null) {
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            return categoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return categoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.findCategoryList(null);
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

}
