package com.lys.lys_blog.service;

import com.lys.lys_blog.mapper.BlogMapper;
import com.lys.lys_blog.pojo.Blog;
import com.lys.lys_blog.pojo.Type;
import com.lys.lys_blog.queryVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogService {
    List<BlogQuery> listBlogs();

    int saveBlog(Blog blog);

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    void deleteBlog(Long id);

    List<FirstPageBlog> getFirstPageBlogs();

    List<RecommendBlog> recommendedBlogs();

    List<FirstPageBlog> searchBlogs(String query);

    DetailedBlog getDetailedBlog(Long id);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

    List<FirstPageBlog> getBlogsByTypeId(Long id);

    List<ArchiveBlog> getArchivesBlogs();
}
