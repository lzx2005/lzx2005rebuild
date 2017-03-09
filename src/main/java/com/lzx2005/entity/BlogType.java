package com.lzx2005.entity;

import com.alibaba.fastjson.JSON;

import java.util.Date;


/**
 * Created by Lizhengxian on 2017/2/17.
 */
public class BlogType {
    private long blogTypeId;
    private String blogTypeName;
    private Date createTime;
    private int count;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
