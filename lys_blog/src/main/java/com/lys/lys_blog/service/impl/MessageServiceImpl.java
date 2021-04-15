package com.lys.lys_blog.service.impl;

import com.lys.lys_blog.mapper.MessageMapper;
import com.lys.lys_blog.pojo.Message;
import com.lys.lys_blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
@Autowired
private MessageMapper messageMapper;
//存放迭代找出所有子代的集合
  private List<Message> tempReplys = new ArrayList<>();

  //查询留言
    @Override
    public List<Message> listMessage() {
    //查询所有父节点
    List<Message> messages = messageMapper.findByParentIdNull(Long.parseLong("-1"));
        for (Message message : messages) {
            Long id = message.getId();
            String parentNickname1 = message.getNickname();
            List<Message> childMessages = messageMapper.findByParentIdNotNull(id);
            //查询出子留言
            combineChileren(childMessages,parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }

        return messages;
    }

    @Override
    public void saveMessage(Message message) {
        message.setCreateTime(new Date());
        messageMapper.saveMessage(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageMapper.deleteMessage(id);
    }

    //查询出子留言
    private void combineChileren(List<Message> childMessages,String parentNickname1) {
//判断是否有一级子回复
    if (childMessages.size() > 0) {
    //循环找出子留言的id
        for (Message childMessage : childMessages) {
            String parentNickname = childMessage.getNickname();
            childMessage.setParentNickname(parentNickname1);
            tempReplys.add(childMessage);
            Long childId = childMessage.getId();
            //查询二级以及所有子集回复
            recursively(childId,parentNickname);
        }
    }

    }
//循环迭代找出子集回复
    private void recursively(Long childId,String parentNickname1) {
    //根据子一级留言id找到子二级留言
    List<Message> replayMessages = messageMapper.findByReplayId(childId);
    if (replayMessages.size() > 0) {
        for (Message replayMessage : replayMessages) {
            Long replayId = replayMessage.getId();
            String parentNickname = replayMessage.getNickname();
            replayMessage.setParentNickname(parentNickname1);
            tempReplys.add(replayMessage);
            //循环迭代找出子集回复
            recursively(replayId,parentNickname);
        }
    }
    }
}
