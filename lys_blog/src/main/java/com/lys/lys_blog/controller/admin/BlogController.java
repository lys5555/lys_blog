package com.lys.lys_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.pojo.Blog;
import com.lys.lys_blog.pojo.Type;
import com.lys.lys_blog.pojo.User;
import com.lys.lys_blog.queryVo.BlogQuery;
import com.lys.lys_blog.queryVo.ShowBlog;
import com.lys.lys_blog.service.BlogService;
import com.lys.lys_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    //查询博客
    @RequestMapping("/blogs")
    public String listBlogs (Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        //按照排序字段，倒叙排序
        String orderBy = "update_time desc";
        //开始分页
        PageHelper.startPage(pageNum,8,orderBy);
        List<BlogQuery> blogs = blogService.listBlogs();
        if (blogs != null) {
            PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
            model.addAttribute("pageInfo",pageInfo);
        }
        List<Type> types = typeService.listTypes();
        model.addAttribute("types",types);
        return "admin/blogs";
    }
    //跳转新增页面
    @GetMapping("/blogs/input")
    public String input (Model  model) {
        model.addAttribute("types",typeService.listTypes());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }
    //新增页面
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        blog.setTypeId(blog.getType().getId());
        blog.setUserId(blog.getUser().getId());

        blog.setFlag(blog.getFlag());
        int f = blogService.saveBlog(blog);
        if (f  == 0) {
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
       return "redirect:/admin/blogs";
    }
//    跳转到编辑博客页面
    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id, Model model) {
       ShowBlog blog = blogService.getBlogById(id);
        List<Type> types = typeService.listTypes();
        model.addAttribute("blog",blog);
        model.addAttribute("types",types);
        return "admin/blogs-input";
    }
//    编辑博客
    @PostMapping("/blogs/{id}")
    public String editPost(ShowBlog showBlog, RedirectAttributes attributes) {
       int f =  blogService.updateBlog(showBlog);
       if (f == 0) {
           attributes.addFlashAttribute("message","修改失败");
       } else {
           attributes.addFlashAttribute("message","修改成功");
       }
       return "redirect:/admin/blogs";
    }
//    删除博客
    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
         blogService.deleteBlog(id);
         attributes.addFlashAttribute("message","删除成功");
         return "redirect:/admin/blogs";
    }
}
