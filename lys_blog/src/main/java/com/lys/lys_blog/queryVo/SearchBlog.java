package com.lys.lys_blog.queryVo;

/**
 * @author: 清峰
 * @date: 2020/9/22 13:26
 * @code: 愿世间永无Bug!
 * @description: 搜索博客管理列表
 */
public class SearchBlog {

    private String title;
    private Long typeId;

    @Override
    public String toString() {
        return "SearchBlog{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public SearchBlog() {
    }
}
