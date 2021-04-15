package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> listTypes();
    Type getByTypeName(String typeName);
    int saveType(Type type);

    Type getTypeById(Long id);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> listTypesAndBlogs();
}
