package com.lys.lys_blog.controller;

import com.lys.lys_blog.pojo.Message;
import com.lys.lys_blog.pojo.User;
import com.lys.lys_blog.service.MessageService;
import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageShowController {
    @Autowired
    private MessageService messageService;
    @Value("${message.avatar}")
    private String avatar;

    //留言展示页面
    @GetMapping("/message")
    public String message() {
        return "message";
    }
    //异步查询留言信息
    @GetMapping("/messagecomment")
    public String messages(Model model) {
       List<Message> messages = messageService.listMessage();
        System.out.println(messages);
        model.addAttribute("messages",messages);
        return "message :: messageList";
    }
//    添加留言
    @PostMapping("/message")
    public String post(Message message, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //设置头像
        if (user != null) {
        message.setAvatar(user.getAvatar());
        message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        System.out.println("准备保存留言");
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        System.out.println("保存完留言"+messages);
        model.addAttribute("messages",messages);
        return "message :: messageList";
    }
//    删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        messageService.deleteMessage(id);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages",messages);
        return "redirect:/message";
    }
}
