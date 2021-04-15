package com.lys.lys_blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lys.lys_blog.pojo.Type;
import com.lys.lys_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
   private TypeService typeService;
    @GetMapping("/types")
    public String listTypes(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        //按照排序字段，倒叙排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,8,orderBy);
        List<Type> types = typeService.listTypes();
        if (types != null) {
            PageInfo<Type> pageInfo = new PageInfo<>(types);
            model.addAttribute("pageInfo",pageInfo);
        }
        return "admin/types";
    }
    //跳转到新增分类页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }
    //新增分类
    @PostMapping("/types")
    public String post(@Valid Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getByTypeName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message","不能添加重复分类");
            return "redirect:/admin/types/input";
        }
        int b = typeService.saveType(type);
        if (b == 0) {
            attributes.addFlashAttribute("message","新增失败！");
        }  else {
            attributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/types";
    }
    //跳转到编辑分类页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/types-input";
    }
    //编辑保存
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,RedirectAttributes redirectAttributes) {
       int t =  typeService.updateType(type);
       if (t == 0) {
           redirectAttributes.addFlashAttribute("message","编辑保存失败！");

       }else {
           redirectAttributes.addFlashAttribute("message","编辑保存成功!");
       }
       return "redirect:/admin/types";
    }
    //删除分类
    @GetMapping("/types/{id}/delete")
    public  String deleteType(@PathVariable Long id,RedirectAttributes attributes) {
        int  i =  typeService.deleteType(id);
        if (i == 0) {
         attributes.addFlashAttribute("message","删除失败！");

        }else {
            attributes.addFlashAttribute("meaasge","删除成功！");
        }
        return "redirect:/admin/types";
    }
}
