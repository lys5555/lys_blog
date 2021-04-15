package com.lys.lys_blog.service.impl;

import com.lys.lys_blog.mapper.UserMapper;
import com.lys.lys_blog.pojo.User;
import com.lys.lys_blog.service.UserService;
import com.lys.lys_blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUsernameAndPassword(String username, String password) {
        return userMapper.checkUsernameAndPassword(username, MD5Utils.code(password));
    }
}
