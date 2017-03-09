package com.lzx2005.web.restfull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzx2005.dao.BlogTypeDao;
import com.lzx2005.dao.ImageDao;
import com.lzx2005.dto.AjaxResult;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Blog;
import com.lzx2005.entity.BlogType;
import com.lzx2005.entity.Image;
import com.lzx2005.entity.User;
import com.lzx2005.service.BlogService;
import com.lzx2005.tool.Log;
import com.lzx2005.tool.StrTool;
import org.apache.ibatis.mapping.ResultMap;
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
import java.util.*;

/**
 * Created by Restfull on 2016/6/29.
 * 公共接口
 */
@Controller
@RequestMapping("/public")
public class PublicRestfulController {


    @Autowired
    BlogTypeDao blogTypeDao;


    @RequestMapping(
            value = "is_not_login",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<String> createBlog(HttpServletRequest res, HttpServletResponse response){
       return new AjaxResult<String>(false,"未登陆",null);
    }


    @RequestMapping(
            value = "message_submit",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult<String> messageSubmit(HttpServletRequest res, HttpServletResponse response){
        return new AjaxResult<String>(false,"该功能暂未开发，尽情期待",null);
    }

    @RequestMapping(
            "/getAllBlogType"
    )
    @ResponseBody
    public AjaxResult<JSONArray> getAllBlogType(){

        List<BlogType> list = blogTypeDao.findAllTypeGroupByBlogs();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);


        return new AjaxResult<JSONArray>(true,"请求成功",jsonArray);
    }

}
