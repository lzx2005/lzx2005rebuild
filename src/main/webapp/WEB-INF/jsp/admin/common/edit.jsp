<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>添加一篇Markdown文章</title>

    <%@include file="../layout/css.jsp"%>
    <%@include file="../layout/script.jsp"%>
</head>

<body>
<%@include file="../layout/nav.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../layout/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">添加一篇文章</h1>
            <form class="form-horizontal" role="form" onsubmit="return saveBlog()">
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">标题：</label>
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="title" placeholder="请输入标题" name="title" value="${blog.data.title }">
                        <input type="hidden" class="form-control" id="blog_id" name="blog_id" value="${blog.data.blogId }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">描述：</label>
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="desc" placeholder="请输入描述" name="desc" value="${blog.data.description }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">Markdown：</label>
                    <div class="col-sm-11">
                        <textarea class="form-control" rows="10" id="content" name="content">${blog.data.content }</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-11">
                        <button type="submit" class="btn btn-primary btn-block">发布</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveBlog() {
        var title = $("#title").val();
        var content = $("#content").val();
        var desc = $("#desc").val();
        var blog_id = $("#blog_id").val();
        if(title==""){
            alert("请填写博客标题");
        }else if(content=="") {
            alert("请填写Markdown内容");
        }else if(desc==""){
            alert("请填写描述内容");
        }else{
            $.post("<%=basePath%>admin_restful/blog/edit", {
                title : title,
                content : content,
                desc : desc,
                blog_id : blog_id
            }, function(data, status) {
                console.log(data);
                if(data['success']==true){
                    alert("文章修改成功");
                    window.location.href("<%=basePath%>admin/blog");
                }else{
                    alert("文章修改失败:"+data['msg']);
                }
            });
        }
        return false;
    }
</script>
</body></html>