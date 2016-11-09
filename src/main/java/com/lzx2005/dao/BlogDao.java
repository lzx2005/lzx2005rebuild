package com.lzx2005.dao;

import com.lzx2005.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public interface BlogDao {
    public int insertBlog(@Param("title") String title,@Param("author") String author,@Param("description") String description,@Param("content") String content,@Param("blogType") short blogType);

    public int insertBlogWithMarkdown(@Param("title") String title,@Param("author") String author,@Param("description") String description,@Param("content") String content,@Param("blogType") short blogType,@Param("markdown") short markdown);

    public Blog queryById(long blogId);

    public ArrayList<Blog> findAll(@Param("offset") int offset, @Param("limit") int limit);

    public long countBlog();

    public int deleteBlog(@Param("blogId")long blogId);

    public int updateBlog(Blog blog);
}
