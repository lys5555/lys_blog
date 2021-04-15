package com.lys.lys_blog.mapper;

import com.lys.lys_blog.pojo.Blog;
import com.lys.lys_blog.queryVo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper {
    List<BlogQuery> listBlogs();

    int saveBlog(Blog blog);

    int updateBlog(ShowBlog showBlog);

    ShowBlog getBlogById(Long id);

    void deleteBlog(Long id);

    List<FirstPageBlog> getFirstPageBlogs();

    List<RecommendBlog> recommendedBlogs();

    List<FirstPageBlog> searchBlogs(String query);

    DetailedBlog getDetailedBlog(Long id);

    void updateViews(Long id);

    void getCommentCountById(Long id);

    Integer getBlogTotal();

    Integer getBlogViewTotal();

    Integer getBlogCommentTotal();

    Integer getBlogMessageTotal();

    List<FirstPageBlog> getBlogsByTypeId(Long id);

    List<ArchiveBlog> getArchivesBlogs();

}
