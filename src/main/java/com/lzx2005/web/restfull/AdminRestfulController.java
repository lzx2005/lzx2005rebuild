package com.lzx2005.web.restfull;

import com.lzx2005.dao.ImageDao;
import com.lzx2005.dto.AjaxResult;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Blog;
import com.lzx2005.entity.Image;
import com.lzx2005.entity.User;
import com.lzx2005.service.BlogService;
import com.lzx2005.tool.Log;
import com.lzx2005.tool.StrTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Restfull on 2016/6/29.
 * 编写Restful接口
 */
@Controller
@RequestMapping("/admin_restful")
public class AdminRestfulController {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private BlogService blogService;

    /**
     * 测试接口
     * @param testStr 测试字符串
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "test/{testStr}/restful_test", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public HashMap<String,String> test(@PathVariable("testStr")String testStr, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put(testStr,testStr);
        map.put(testStr+"1",testStr+"2");
        return map;
    }


    /**
     * 上传图片接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(
            value = "image/upload",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<String> upload(HttpServletRequest request, HttpServletResponse response) {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(!myFileName.trim().equals("")){
                        System.out.println(myFileName);



                        //重命名上传后的文件名
                        String[] str = myFileName.split("\\.");
                        String type = str[str.length-1];
                        boolean isImage = type.equalsIgnoreCase("jpg")||
                                        type.equalsIgnoreCase("png")||
                                        type.equalsIgnoreCase("jpeg")||
                                        type.equalsIgnoreCase("gif")||
                                        type.equalsIgnoreCase("bmp");
                        if(!isImage){
                            AjaxResult<String> result = new AjaxResult<String>(false,"请上传一张图片",null);
                            return result;
                        }
                        String fileName = "img" + System.currentTimeMillis() + "." + str[str.length-1];
                        //定义上传路径
                        String path=request.getSession().getServletContext().getRealPath("/resources/upload_img/");
                        //Log.e(this.getClass().getName(),path);
                        File localFile = new File(path+File.separator+fileName);
                        try {
                            file.transferTo(localFile);
                            Image image = new Image();
                            image.setName(fileName);
                            image.setRelativePath("/resources/upload_img/"+fileName);
                            image.setAbsolutePath(localFile.getAbsolutePath());
                            image.setSize(file.getSize());
                            int success = imageDao.addImageReturnId(image);
                            if(success==1){
                                AjaxResult<String> result = new AjaxResult<String>(true,image.getImageId()+"","/resources/upload_img/"+fileName);
                                return result;
                            }
                        } catch (IOException e) {
                            Log.e(this.getClass().getName(),"上传文件保存失败",e);
                            e.printStackTrace();
                        }
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }

        AjaxResult<String> result = new AjaxResult<String>(false,"上传失败",null);
        return result;
    }


    /**
     * 创建文章
     * @param res
     * @param response
     * @return
     */
    @RequestMapping(
            value = "blog/create",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Blog> createBlog(HttpServletRequest res, HttpServletResponse response){
        String title = res.getParameter("title");
        String content = res.getParameter("content");
        String desc = res.getParameter("desc");

        if(!StrTool.allIsNotNull(title,content,desc)){
            return new AjaxResult<Blog>(false,"缺少参数",null);
        }

        User user = (User)res.getSession().getAttribute("user");
        String author = user.getUsername();
        ServiceResult<Blog> result = blogService.createBlog(title, author,desc, content, (short) 0, (short) 1);
        if(result.isSuccess()){
            return new AjaxResult<Blog>(true,"文章发布成功",result.getData());
        }else{
            return new AjaxResult<Blog>(false,result.getErrorMsg(),null);
        }
    }



    @RequestMapping(
            value = "blog/edit",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Blog> editBlog(HttpServletRequest res, HttpServletResponse response){
        String title = res.getParameter("title");
        String content = res.getParameter("content");
        String desc = res.getParameter("desc");
        Long blog_id = Long.parseLong(res.getParameter("blog_id"));

        if(!StrTool.allIsNotNull(title,content,desc)){
            return new AjaxResult<Blog>(false,"缺少参数",null);
        }

        if(blog_id==null){
            return new AjaxResult<Blog>(false,"缺少参数",null);
        }

        ServiceResult<Blog> result = blogService.getBlog(blog_id);

        if(result.isSuccess()){
            //找到Blog
            Blog blog = result.getData();
            User user = (User)res.getSession().getAttribute("user");
            String author = user.getUsername();
            blog.setTitle(title);
            blog.setAuthor(author);
            blog.setContent(content);
            blog.setDescription(desc);
            ServiceResult<Blog> blogServiceResult = blogService.editBlog(blog);
            if(blogServiceResult.isSuccess()){
                return new AjaxResult<Blog>(true,"文章修改成功",result.getData());
            }else{
                return new AjaxResult<Blog>(false,blogServiceResult.getErrorMsg(),null);
            }
        }else{
            return new AjaxResult<Blog>(false,result.getErrorMsg(),null);
        }
    }


    /**
     * 删除文章
     * @param blogId    文章Id
     * @return
     */
    @RequestMapping(
            value = "blog/delete",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<Blog> deleteBlog(@RequestParam("blogId")long blogId){
        ServiceResult<Blog> result = blogService.getBlog(blogId);
        if(result.isSuccess() && result.getData()!=null){
            //找到blog
            Blog blog = result.getData();
            ServiceResult<Blog> result1 = blogService.deleteBlog(blogId);
            if(result1.isSuccess()){
                return new AjaxResult<Blog>(true,"文章已经被成功删除",result.getData());
            }else{
                return new AjaxResult<Blog>(false,"文章删除失败",null);
            }
        }else{
            return new AjaxResult<Blog>(false,"删除失败，找不到该文章",null);
        }
    }
}
