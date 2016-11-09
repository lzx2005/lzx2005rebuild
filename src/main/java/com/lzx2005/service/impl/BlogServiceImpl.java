package com.lzx2005.service.impl;

import com.lzx2005.dao.BlogDao;
import com.lzx2005.dto.PageResult;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Blog;
import com.lzx2005.service.BlogService;
import com.lzx2005.tool.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/20.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    public ServiceResult<Blog> createBlog(String title, String author,String desc, String content, short blogType) {
        return createBlog(title, author, content, desc, blogType,(short)0);
    }

    public ServiceResult<Blog> createBlog(String title, String author,String desc, String content, short blogType, short markdown) {
        int result = blogDao.insertBlogWithMarkdown(title, author,desc, content, blogType,markdown);
        ServiceResult<Blog> sr = null;
        Blog blog = null;
        if (result == 1) {
            sr = new ServiceResult<Blog>(true, blog);
        } else {
            sr = new ServiceResult<Blog>(false, "can't save,unknow reason");
        }
        return sr;
    }

    public ServiceResult<PageResult<Blog>> getAllBlog(int page, int pageSize) {
        //计算共多少条Blog
        long countBlog = blogDao.countBlog();

        //计算一共多少页


        int totalPage = new PageTool().getPage(countBlog,pageSize);
        System.out.println(totalPage);

        PageResult<Blog> pageResult = new PageResult<Blog>();
        pageResult.setCurPage(page);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalRow(countBlog);
        pageResult.setTotalPage(totalPage);
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        ArrayList<Blog> blogs = blogDao.findAll(offset, limit);
        pageResult.setList(blogs);
        return new ServiceResult<PageResult<Blog>>(true,pageResult);
    }

    public ServiceResult<Blog> getBlog(long blogId) {
        Blog blog = blogDao.queryById(blogId);
        ServiceResult<Blog> sr = null;
        if (blog == null) {
            //没有找到该博客
            sr = new ServiceResult<Blog>(false, "can't find this blog");
        } else {
            sr = new ServiceResult<Blog>(true, blog);
        }
        return sr;
    }

    public ServiceResult<Blog> deleteBlog(long blogId) {

        int deleteCount = blogDao.deleteBlog(blogId);
        if(deleteCount==1){
            //删除成功
            return new ServiceResult<Blog>(true,"删除成功");
        }else{
            return new ServiceResult<Blog>(false,"删除失败");
        }
    }

    public ServiceResult<Blog> editBlog(Blog blog) {
        if(blog!=null){
            int i = blogDao.updateBlog(blog);
            if(i==1){
                return new ServiceResult<Blog>(true,blog);
            }else{

                return new ServiceResult<Blog>(false,"保存失败");
            }
        }else{
            return new ServiceResult<Blog>(false,"传入参数为空");
        }
    }
}
