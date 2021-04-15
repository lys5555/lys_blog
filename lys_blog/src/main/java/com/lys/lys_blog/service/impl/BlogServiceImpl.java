package com.lys.lys_blog.service.impl;

import com.lys.lys_blog.NotFountException;
import com.lys.lys_blog.mapper.BlogMapper;
import com.lys.lys_blog.pojo.Blog;
import com.lys.lys_blog.queryVo.*;
import com.lys.lys_blog.service.BlogService;
import com.lys.lys_blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public List<BlogQuery> listBlogs() {
        return blogMapper.listBlogs();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogMapper.updateBlog(showBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlogs() {
        return blogMapper.getFirstPageBlogs();
    }

    @Override
    public List<RecommendBlog> recommendedBlogs() {
        return blogMapper.recommendedBlogs();
    }

    @Override
    public List<FirstPageBlog> searchBlogs(String query) {
        return blogMapper.searchBlogs(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFountException("详情博客信息未找到");
        }
        String content = detailedBlog.getContent();
        String toHtmlExtensions = MarkdownUtils.markdownToHtmlExtensions(content);
        detailedBlog.setContent(toHtmlExtensions);
        blogMapper.getCommentCountById(id);
        blogMapper.updateViews(id);
        return detailedBlog;
    }

    @Override
    public Integer getBlogTotal() {
        return blogMapper.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        Integer blogViewTotal = blogMapper.getBlogViewTotal();
        return blogMapper.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return blogMapper.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return blogMapper.getBlogMessageTotal();
    }

    @Override
    public List<FirstPageBlog> getBlogsByTypeId(Long id) {
        return blogMapper.getBlogsByTypeId(id);
    }

    @Override
    public List<ArchiveBlog> getArchivesBlogs() {
        return blogMapper.getArchivesBlogs();
    }
}
