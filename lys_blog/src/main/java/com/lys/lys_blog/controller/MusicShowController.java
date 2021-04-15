package com.lys.lys_blog.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicShowController {
    @GetMapping("/music")
    public String music() {
        return "music";
    }
}
