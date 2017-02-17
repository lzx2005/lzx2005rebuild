package com.lzx2005.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/19.
 */

public class Blog {

    private long blogId;
    private String title;
    private String content;
    private String author;
    private String description;
    private Date createTime;
    private long view;
    private long blogType;
    private String tags;

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
        if (createTime == null) {
            return null;
        }
        return (Date) createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        if (createTime == null) {
            this.createTime = null;
        } else {
            this.createTime = (Date) createTime.clone();
        }
    }

    public long getView() {
        return view;
    }

    public void setView(long view) {
        this.view = view;
    }

    public long getBlogType() {
        return blogType;
    }

    public void setBlogType(long blogType) {
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
                ", tags='" + tags + '\'' +
                ", markdown=" + markdown +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        /*return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(blogId, blog.blogId).
                        append(view, blog.view).
                        isEquals();*/

        if (blogId != blog.blogId) return false;
        if (view != blog.view) return false;
        if (blogType != blog.blogType) return false;
        if (markdown != blog.markdown) return false;
        if (title != null ? !title.equals(blog.title) : blog.title != null) return false;
        if (content != null ? !content.equals(blog.content) : blog.content != null) return false;
        if (author != null ? !author.equals(blog.author) : blog.author != null) return false;
        if (description != null ? !description.equals(blog.description) : blog.description != null) return false;
        if (createTime != null ? !createTime.equals(blog.createTime) : blog.createTime != null) return false;
        return tags != null ? tags.equals(blog.tags) : blog.tags == null;

    }

    @Override
    public int hashCode() {
        /*new HashCodeBuilder()
                .append()*/
        int result = (int) (blogId ^ (blogId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (int) (view ^ (view >>> 32));
        result = 31 * result + (int) blogType;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (int) markdown;
        return result;
    }
}
