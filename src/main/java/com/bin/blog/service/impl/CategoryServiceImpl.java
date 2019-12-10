package com.bin.blog.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bin.blog.dao.BlogMapper;
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
    @Autowired
    private BlogMapper blogMapper;
    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
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
    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
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
    /**
     * 修改分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
    	   Category blogCategory = categoryMapper.selectByCategoryName(categoryName);
    	   if (blogCategory == null) {
    		   blogCategory=new  Category();
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryId(categoryId);
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
