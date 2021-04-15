package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.Comment;

import java.util.List;

public interface CommentService {
   List<Comment> listCommentByBlogId(Long id);
   void saveComment(Comment comment);

   void deleteComment(Comment comment, Long id);
}
