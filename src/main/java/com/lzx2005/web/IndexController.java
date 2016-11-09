package com.lzx2005.web;

import com.lzx2005.dto.AjaxResult;
import com.lzx2005.dto.PageResult;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Blog;
import com.lzx2005.entity.User;
import com.lzx2005.service.BlogService;
import com.lzx2005.service.UserService;
import com.lzx2005.setting.Var;
import com.lzx2005.tool.Log;
import com.lzx2005.tool.StrTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Administrator on 2016/6/28.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;
    /**
     * 首页
     * @param model
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest res,Model model){
        String page = res.getParameter("page");
        int pageint = 1;
        if(StrTool.isNotNull(page)){
            pageint = Integer.parseInt(page);
        }
        ServiceResult<PageResult<Blog>> allBlog = blogService.getAllBlog(pageint, Var.DEFAULT_PAGE_SIZE);
        model.addAttribute("blogs",allBlog);
        return "common/index";
    }


    @RequestMapping(value = "/blog/{blogId}/overview", method = RequestMethod.GET)
    public String blogOverview(@PathVariable("blogId")long blogId,Model model){
        ServiceResult<Blog> result = blogService.getBlog(blogId);
        if(result.isSuccess()){
            //查找成功
            model.addAttribute(result.getData());
            return "common/blog";
        }else{
            //查找失败
            return "redirect:/";
        }
    }



    /**
     * 登陆逻辑
     * @param res
     * @return ajaxResult
     */
    @RequestMapping(
            value = "/login_sub",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public AjaxResult<User> login_sub(HttpServletRequest res){
        String username = res.getParameter("username");
        String password = res.getParameter("password");
        AjaxResult<User> ajaxResult = null;
        ServiceResult<User> serviceResult = userService.login(username, password);
        if(serviceResult.isSuccess()){
            //登陆成功
            User user = serviceResult.getData();
            res.getSession().setAttribute("user",user);
            ajaxResult = new AjaxResult<User>(true, "登陆成功", user);
        }else{
            //登陆失败
            ajaxResult = new AjaxResult<User>(false,"登陆失败，用户名或者密码错误",null);
        }

        return ajaxResult;
    }


    /**
     * 退出登录逻辑
     * @param res
     * @param model
     * @return
     */
    @RequestMapping(value = "/log_out", method = RequestMethod.GET)
    public String log_out(HttpServletRequest res,Model model){
        System.out.println("退出登录");
        res.getSession().removeAttribute("user");
        res.getAttribute("user");
        return "redirect:/";
    }
}
