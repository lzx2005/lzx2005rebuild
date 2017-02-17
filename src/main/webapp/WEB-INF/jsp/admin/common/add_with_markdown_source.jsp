<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath()+"/";
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
                        <input type="text" class="form-control" id="title" placeholder="请输入标题" name="title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">描述：</label>
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="desc" placeholder="请输入描述" name="desc">
                    </div>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">类型：</label>
                    <div class="col-sm-11">
                        <p class="form-control-static" id="loading">读取中...</p>
                        <select class="form-control" name="blog_type" id="blog_type" style="display: none">
                            <option value="0">默认</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">Markdown：</label>
                    <div class="col-sm-11">
                        <textarea class="form-control" rows="30" id="content" name="content">
## Markdown Source Here
### 反馈与建议

- 微博：[@lzx2005](http://weibo.com/u/2557929062)
- 邮箱：&lt;crow2005@vip.qq.com&gt;
                        </textarea>
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
    $(function () {
        //当页面加载完成
        //1.发起请求获取文章类型
        $.post("<%=basePath%>admin_restful/blog/get_all_type",{
            a:1
        },function (data,status) {
            //2.填到select里面
            var list = data['data'];
            var blogTypeSelect = $("#blog_type");
            for(var i=0;i<list.length;i++){
                var type = list[i];
                console.log(type['blogTypeId'],type['blogTypeName']);
                blogTypeSelect.append('<option value="'+type['blogTypeId']+'">'+type['blogTypeName']+'</option>');

            }
            setTimeout(function () {
                $("#loading").hide();
                blogTypeSelect.show();
            }, 1000);
            console.log('加载完毕');
        });
    })


    function saveBlog() {
        var title = $("#title").val();
        var content = $("#content").val();
        var desc = $("#desc").val();
        var blog_type = $("#blog_type").val();
        if(title==""){
            alert("请填写博客标题");
        }else if(content=="") {
            alert("请填写Markdown内容");
        }else if(desc==""){
            alert("请填写描述内容");
        }else if(blog_type==""){
            alert("请填写类型");
        }else{
            $.post("<%=basePath%>admin_restful/blog/create", {
                title : title,
                content : content,
                desc : desc,
                blog_type : blog_type,
            }, function(data, status) {
                console.log(data);
                if(data['success']==true){
                    alert("文章发布成功");
                    window.location.reload();
                }else{
                    alert("文章发布失败:"+data['msg']);
                }
            });
        }
        return false;
    }
</script>
</body></html>