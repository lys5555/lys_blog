package com.lys.lys_blog.service.impl;

import com.lys.lys_blog.mapper.TypeMapper;
import com.lys.lys_blog.pojo.Type;
import com.lys.lys_blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> listTypes() {
        return typeMapper.listTypes();
    }

    @Override
    public Type getByTypeName(String typeName) {
        return typeMapper.getTypeByName(typeName);
    }

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    @Override
    public List<Type> listTypesAndBlogs() {
        return typeMapper.listTypesAndBlogs();
    }
}
