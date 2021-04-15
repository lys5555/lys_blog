package com.lys.lys_blog.controller;

import com.lys.lys_blog.pojo.FriendLink;
import com.lys.lys_blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendLinkShowController {
    @Autowired
    private FriendLinkService friendLinkService;
    @GetMapping("/friends")
    public String friendLink(Model model){
        List<FriendLink> frendlinks = friendLinkService.listFriendLinks();
        model.addAttribute("frendlinks",frendlinks);
        return "friends";
    }
}
