package com.lys.lys_blog.controller;

import com.lys.lys_blog.service.PictureService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PictureShowController {
    @Autowired
    private PictureService pictureService;
    @GetMapping("picture")
    public String picture(Model model) {
        model.addAttribute("pictures",pictureService.listPicture());
        return "picture";
    }
}
