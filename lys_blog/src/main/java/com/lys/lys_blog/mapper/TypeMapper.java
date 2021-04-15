package com.lys.lys_blog.mapper;

import com.lys.lys_blog.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {
    List<Type> listTypes();

    Type getTypeByName(String typeName);

    int saveType(Type type);

    Type getTypeById(Long id);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> listTypesAndBlogs();

}
