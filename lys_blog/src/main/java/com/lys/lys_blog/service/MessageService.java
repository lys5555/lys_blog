package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> listMessage();

    void saveMessage(Message message);

    void deleteMessage(Long id);

}
