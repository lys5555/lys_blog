package com.lys.lys_blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.pojo.Picture;
import com.lys.lys_blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    //查询所有照片墙
    @GetMapping("/pictures")
    public String listPicture(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
       List<Picture> pictures = pictureService.listPicture();
        PageHelper.startPage(pageNum, 8);
        PageInfo<Picture> pageInfo =  new PageInfo<>(pictures);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }
    //跳转到新增照片页面
    @GetMapping("pictures/input")
    public String input(Model model) {
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }
    //新增照片
    @PostMapping("/pictures")
    public String addPicture(@Valid Picture picture, RedirectAttributes attributes) {
       int f = pictureService.savePicture(picture);
       if (f == 0) {
           attributes.addFlashAttribute("message","新增失败");
       } else {
           attributes.addFlashAttribute("message","新增成功");
       }
        return "redirect:/admin/pictures";
    }
    //跳转到编辑图片页面
    @GetMapping("/pictures/{id}/input")
    public String editPicture(Model model,@PathVariable Long id){
       Picture picture = pictureService.getPictureById(id);
       if (picture != null) {
           model.addAttribute("picture",picture);
       }
       return "admin/pictures-input";
    }
    //编辑图片
    @PostMapping("/pictures/{id}")
    public String editPost(@Valid Picture picture,RedirectAttributes attributes) {
       int f =  pictureService.updatePicture(picture);
       if (f == 0) {
           attributes.addFlashAttribute("message","更新失败！");
       } else {
           attributes.addFlashAttribute("message","更新成功");
       }
       return "redirect:/admin/pictures";
    }
    //删除图片
    @GetMapping("/pictures/{id}/delete")
    public String deletePicture(@PathVariable Long id,RedirectAttributes attributes) {
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/pictures";
    }

}
