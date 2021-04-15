package com.lys.lys_blog.mapper;

import com.lys.lys_blog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User checkUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
