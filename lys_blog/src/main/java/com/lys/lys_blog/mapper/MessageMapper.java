package com.lys.lys_blog.mapper;

import com.lys.lys_blog.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
//查询父集留言
    List<Message> findByParentIdNull(@Param("parentId") Long parentId);
    //查询一级留言
    List<Message> findByParentIdNotNull(@Param("id") Long id);
//查询二级留言以及子集回复
    List<Message> findByReplayId(@Param("childId") Long childId);
//保存留言
    void saveMessage(Message message);
//删除留言
    void deleteMessage(Long id);
}
