package com.lzx2005.dao;

import com.lzx2005.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public interface BlogDao {

    public int insertBlog(@Param("title") String title,
                          @Param("author") String author,
                          @Param("description") String description,
                          @Param("content") String content,
                          @Param("blogType") long blogType,
                          @Param("markdown") short markdown,
                          @Param("tags") String tags);

    public Blog queryById(long blogId);

    public ArrayList<Blog> findAll(@Param("offset") int offset, @Param("limit") int limit);

    public long countBlog();

    public long countBlogByType(long blogTypeId);

    public int deleteBlog(@Param("blogId")long blogId);

    public int updateBlog(Blog blog);

    public List<Blog> findAllByBlogType(long blogTypeId);

    public ArrayList<Blog> findAllByBlogTypeAndLimit(@Param("offset") int offset, @Param("limit") int limit,@Param("blogTypeId") long blogTypeId);
}
