package com.lzx2005.entity;

/**
 * Created by Lizhengxian on 2017/2/17.
 */
public class BlogType {
    private long blogTypeId;
    private String blogTypeName;

    public long getBlogTypeId() {
        return blogTypeId;
    }

    public void setBlogTypeId(long blogTypeId) {
        this.blogTypeId = blogTypeId;
    }

    public String getBlogTypeName() {
        return blogTypeName;
    }

    public void setBlogTypeName(String blogTypeName) {
        this.blogTypeName = blogTypeName;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "blogTypeId=" + blogTypeId +
                ", blogTypeName='" + blogTypeName + '\'' +
                '}';
    }
}
