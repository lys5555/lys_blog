package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.User;

public interface UserService {
    User checkUsernameAndPassword(String username, String password);
}
