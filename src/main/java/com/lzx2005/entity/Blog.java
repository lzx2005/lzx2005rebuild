package com.lzx2005.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/19.
 */

public class Blog {

    @Id
    private long blogId;
    private String title;
    private String content;
    private String author;
    private String description;
    private Date createTime;
    private long view;
    private short blogType;

    private short markdown;

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public short getBlogType() {
        return blogType;
    }

    public void setBlogType(short blogType) {
        this.blogType = blogType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public short getMarkdown() {
        return markdown;
    }

    public void setMarkdown(short markdown) {
        this.markdown = markdown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", view=" + view +
                ", blogType=" + blogType +
                ", markdown=" + markdown +
                '}';
    }
}
