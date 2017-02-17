package com.lzx2005.service;

import com.lzx2005.dto.PageResult;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Blog;
import com.lzx2005.entity.BlogType;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public interface BlogService {

    public ServiceResult<Blog> createBlog(String title,
                                          String author,
                                          String desc,
                                          String content,
                                          long blogType,
                                          short markdown,
                                          String tags);

    public ServiceResult<PageResult<Blog>> getAllBlog(int page, int pageSize);

    public ServiceResult<Blog> getBlog(long blogId);

    public ServiceResult<Blog> deleteBlog(long blogId);

    public ServiceResult<Blog> editBlog(Blog blog);

    public ServiceResult<List<BlogType>> findAllBlogType();
}
