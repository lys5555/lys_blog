package com.lys.lys_blog.controller.admin;

import com.lys.lys_blog.pojo.User;
import com.lys.lys_blog.service.UserService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    //跳转到登录页面
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }
    //跳转到首页
    @GetMapping("/index")
    public String index() {
    return "admin/index";
    }
    //登录校验
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUsernameAndPassword(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/admin";
        }

    }
    //注销功能
    @GetMapping("logout")
    public String logout (HttpSession session) {
        session.removeAttribute("user");
        return "admin/login";
    }

}
