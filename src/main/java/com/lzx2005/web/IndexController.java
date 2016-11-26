package com.lzx2005.web;

import com.geetest.sdk.java.GeetestLib;
import com.geetest.sdk.java.web.demo.GeetestConfig;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            long view = result.getData().getView();
            view++;
            result.getData().setView(view);
            blogService.editBlog(result.getData());
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

        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key());

        String challenge = res.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = res.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = res.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) res.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }

        if(gtResult!=1){
            return new AjaxResult<User>(false,"登陆失败，验证码验证失败",null);
        }

        //账号密码验证
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


    @RequestMapping(
            value = "/captcha",
            method = {RequestMethod.GET,RequestMethod.POST},
            produces = {"text/html"})
    @ResponseBody
    public String captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key());

        String resStr = "{}";
        int gtServerStatus = gtSdk.preProcess();
        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        resStr = gtSdk.getResponseStr();
        PrintWriter out = response.getWriter();
        out.println(resStr);
        return null;
    }

}
