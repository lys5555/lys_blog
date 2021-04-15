package com.lys.lys_blog.service;

import com.lys.lys_blog.pojo.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> listPicture();

    int savePicture(Picture picture);

    Picture getPictureById(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);
}
