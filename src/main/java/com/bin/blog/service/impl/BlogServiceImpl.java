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
import com.bin.blog.util.PageQueryUtil;
import com.bin.blog.util.PageResult;

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

	@Override
	public Blog getBlogById(Long blogid) {
		Blog blog=blogMapper.selectByPrimaryKey(blogid);
		return blog;
	}
	@Override
    @Transactional
	public String updateBlog(Blog blog) {
		Blog updateBlog= blogMapper.selectByPrimaryKey(blog.getBlogId());
		if(updateBlog==null){
			return "数据不存在";
		}
		updateBlog.setBlogTitle(blog.getBlogTitle());
		updateBlog.setBlogSubUrl(blog.getBlogSubUrl());
		updateBlog.setBlogContent(blog.getBlogContent());
		updateBlog.setBlogCoverImage(blog.getBlogCoverImage());
		updateBlog.setBlogStatus(blog.getBlogStatus());
		updateBlog.setEnableComment(blog.getEnableComment());
        Category blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if(blogCategory==null){
        	updateBlog.setBlogCategoryId(0);
        	updateBlog.setBlogCategoryName("默认分类");
        }else{
        	updateBlog.setBlogCategoryName(blogCategory.getCategoryName());
        	updateBlog.setBlogCategoryId(blogCategory.getCategoryId()) ;   //分类的排序值加1
        	 blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        	
        }
      //处理标签数据
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        updateBlog.setBlogTags(blog.getBlogTags());
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
          //新增标签数据不为空->新增标签数据
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                tagMapper.batchInsertBlogTag(tagListForInsert);
            }
            List<TagRelation> blogTagRelations = new ArrayList<>();
            //新增关系数据
            allTagsList.addAll(tagListForInsert);
            for (Tag tags1 : allTagsList) {
                TagRelation blogTagRelation = new TagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tags1.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
          //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
            categoryMapper.updateByPrimaryKeySelective(blogCategory);
            //删除原关系数据
            blogTagRelationMapper.deleteByBlogId(blog.getBlogId());
            blogTagRelationMapper.batchInsert(blogTagRelations);
            if (blogMapper.updateByPrimaryKeySelective(updateBlog) > 0) {
                return "success";
            }
            return "修改失败";
        }
	@Override
	public PageResult getBlogsPage(PageQueryUtil pageUtil) {
        List<Blog> blogList = blogMapper.findBlogList(pageUtil);
        int total = blogMapper.getTotalBlogs(pageUtil);
        PageResult pageResult = new PageResult(blogList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

	@Override
	  public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return blogMapper.deleteBatch(ids) > 0;
    }
}

