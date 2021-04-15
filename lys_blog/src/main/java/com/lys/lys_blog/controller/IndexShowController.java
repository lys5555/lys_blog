package com.lys.lys_blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.NotFountException;
import com.lys.lys_blog.mapper.BlogMapper;
import com.lys.lys_blog.pojo.Blog;
import com.lys.lys_blog.pojo.Comment;
import com.lys.lys_blog.queryVo.BlogQuery;
import com.lys.lys_blog.queryVo.DetailedBlog;
import com.lys.lys_blog.queryVo.FirstPageBlog;
import com.lys.lys_blog.queryVo.RecommendBlog;
import com.lys.lys_blog.service.BlogService;
import com.lys.lys_blog.service.CommentService;
import com.lys.lys_blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum,4);
        //第一页博客展示
       List<FirstPageBlog> firstPageBlogs = blogService.getFirstPageBlogs();
       //最热博客推荐
       List<RecommendBlog> recommendBlogs = blogService.recommendedBlogs();
        PageInfo<FirstPageBlog> firstPageBlogPageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo",firstPageBlogPageInfo);
        model.addAttribute("recommendedBlogs",recommendBlogs);
        return "index";
    }
//    搜索博客
    @PostMapping("/search")
    public String searchBlogs(@RequestParam(name = "query") String query,@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,Model model) {
        List<FirstPageBlog> blogQueries = blogService.searchBlogs(query);
        PageHelper.startPage(pageNum,10);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogQueries);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }

    //跳转到博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
       DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
       List<Comment>  comments = commentService.listCommentByBlogId(id);
        System.out.println(comments);
        model.addAttribute("blog",detailedBlog);
        model.addAttribute("comments",comments);
        return "blog";
    }
    //查询底部栏博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.getBlogTotal();
        model.addAttribute("blogTotal",blogTotal);
        int blogViewTotal = blogService.getBlogViewTotal();
        model.addAttribute("blogViewTotal",blogViewTotal);
        int blogCommentTotal = blogService.getBlogCommentTotal();
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        int blogMessageTotal = blogService.getBlogMessageTotal();
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }

}
