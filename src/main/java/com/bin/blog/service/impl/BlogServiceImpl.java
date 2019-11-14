package com.bin.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bin.blog.dao.BlogMapper;
import com.bin.blog.dao.CategoryMapper;
import com.bin.blog.dao.TagMapper;
import com.bin.blog.dao.TagRelationMapper;
import com.bin.blog.entity.Blog;
import com.bin.blog.entity.Category;
import com.bin.blog.entity.Tag;
import com.bin.blog.entity.TagRelation;
import com.bin.blog.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagRelationMapper blogTagRelationMapper;

    @Override
    @Transactional
    public String save(Blog blog) {
        Category blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        System.out.println("问题开始");
        //保存文章
        if (blogMapper.insertSelective(blog) > 0) {
            System.out.println("问题结束");

            //新增的tag对象
            List<Tag> tagListForInsert = new ArrayList<>();
            //所有的tag对象，用于建立关系数据
            List<Tag> allTagsList = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                Tag tag = tagMapper.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增
                    Tag tempTag = new Tag();
                    tempTag.setTagName(tags[i]);
                    tagListForInsert.add(tempTag);
                } else {
                    allTagsList.add(tag);
                }
            }
            //新增标签数据并修改分类排序值
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                tagMapper.batchInsertBlogTag(tagListForInsert);
            }
            categoryMapper.updateByPrimaryKeySelective(blogCategory);
            List<TagRelation> blogTagRelations = new ArrayList<>();
            //新增关系数据
            allTagsList.addAll(tagListForInsert);
            for (Tag tag : allTagsList) {
                TagRelation blogTagRelation = new TagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
               // System.out.println("blogTagRelation");
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "success";
            }
        }
        return "保存失败";
    }
}
