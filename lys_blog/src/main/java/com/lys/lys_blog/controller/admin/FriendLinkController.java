package com.lys.lys_blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.pojo.FriendLink;
import com.lys.lys_blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class FriendLinkController {
    @Autowired
    private FriendLinkService service;
    @GetMapping("/friendlinks")
    public String listFriendLink(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model) {

       List<FriendLink>  list = service.listFriendLinks();
        PageHelper.startPage(pageNum,8);
        PageInfo<FriendLink> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendLinks";
    }
    //跳转到新增友链页面
    @GetMapping("/friendlinks/input")
    public String input(Model model){
        model.addAttribute("friendlink",new FriendLink());
        return "admin/friendlinks-input";
    }
    //新增友链
    @PostMapping("/friendlinks")
    public String post(@Valid FriendLink friendLink, RedirectAttributes attributes){
       FriendLink f1 = service.getFriendLink(friendLink.getBlogaddress());
       if (f1 != null) {
           attributes.addFlashAttribute("message","友链重复,已存在该朋友哟");
           return "redirect:/admin/friendlinks/input";
       }
        int f = service.saveFriendLink(friendLink);
       if (f == 0) {
           attributes.addFlashAttribute("message","新增失败!");
       } else {
           attributes.addFlashAttribute("message","新增成功!");
       }
        return "redirect:/admin/friendlinks";
    }
    //跳转到编辑友链页面
    @GetMapping("/friendlinks/{id}/input")
    public String editInput(@PathVariable Long id,Model model) {
       FriendLink friendLink = service.getFriendLinkById(id);
       model.addAttribute("friendlink",friendLink);
       return "admin/friendlinks-input";
    }
    //编辑友链
    @PostMapping("/friendlinks/{id}")
    public String editPost(@Valid FriendLink friendLink,RedirectAttributes attributes) {
       int  f = service.updateFriendLink(friendLink);
        if (f == 0) {
            attributes.addFlashAttribute("message","修改失败!");
        } else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/friendlinks";
    }
    //删除友链
    @GetMapping("/friendlinks/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes attributes) {
       int f = service.delete(id);
       if (f == 0) {
           attributes.addFlashAttribute("message","删除失败");

       }else {
           attributes.addFlashAttribute("message","删除成功");
       }
       return "redirect:/admin/friendlinks";
    }
}
