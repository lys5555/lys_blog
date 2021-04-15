package com.lys.lys_blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.pojo.Type;
import com.lys.lys_blog.queryVo.FirstPageBlog;
import com.lys.lys_blog.service.BlogService;
import com.lys.lys_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/types/{id}")
    public String listType(@PathVariable Long id, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
       List<Type> types = typeService.listTypesAndBlogs();
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
       List<FirstPageBlog> blogs = blogService.getBlogsByTypeId(id);
        PageHelper.startPage(pageNum,1000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
