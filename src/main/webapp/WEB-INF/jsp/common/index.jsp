<%
    String basePath = request.getContextPath()+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <%@include file="../layout/seo.jsp" %>

    <title>Raven的笔记本</title>
    <base href="<%=basePath %>"/>
    <%@include file="../layout/css.jsp" %>
    <%@include file="../layout/script.jsp" %>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?5bef8f5da35287848ce290a02d44a04e";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>

<body>

<%@include file="../layout/nav.jsp" %>

<div class="jumbotron jumb-block text-center">
    <div class="container jumb-container">
        <h2>Hello!</h2>
        <p>如果发出声音是危险的，那就保持沉默；如果自觉无力发光，那就别去照亮别人。但是，不要习惯了黑暗就为黑暗辩护；不要为自己的苟且而得意洋洋；不要嘲讽那些比自己更勇敢、更有热量的人们。可以卑微如尘土，不可扭曲如蛆虫。</p>
        <p><a class="button button-3d button-primary button-rounded" href="#" role="button">关于我</a></p>
    </div>
</div>

<div class="container">

    <div class="row">
        <div class="col-md-8">
            <c:forEach var="item" items="${blogs.data.list }" varStatus="status">
                <article class="blog-block">
                    <h2 class="text-center line-height-50"><a href="<%=basePath%>blog/${item.blogId}/overview">${item.title }</a></h2>
                    <p class="sec-title text-center line-height-50">作者：${item.author } •<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></p>
                    <p class="">${item.description }</p>
                    <hr/>
                    <div class="button-div">
                        <a class="button button-3d button-primary button-rounded" href="<%=basePath%>blog/${item.blogId}/overview" role="button">阅读全文</a>
                    </div>

                </article>
            </c:forEach>
            <div class="col-md-12 blog-block-page">
                <%@include file="../layout/page.jsp"%>
            </div>


        </div>
        <div class="col-md-4">
            <div class="info-block">
                <h4 class="h2title">基本信息</h4>
                <div class="text-center">
                    <img class="my-avatar" src="<%=basePath%>resources/image/avatar.png">
                </div>
                <div class="text-center">
                    <p>Raven</p>
                    <p>年龄:23</p>
                    <p>性别:男</p>
                    <p>邮箱:<a href="mailto:crow2005@vip.qq.com">crow2005@vip.qq.com</a></p>
                    <p>微博:<a href="http://weibo.com/u/2557929062">lzx2005</a></p>
                </div>
            </div>


            <div class="info-block">
                <h4 class="h2title">文章分类</h4>
                <div id="loadingDiv">
                    Loading...
                </div>
                <div id="blogTypeListDiv" style="display: none">
                </div>
            </div>

            <div class="info-block">
                <h4 class="h2title">本网站源代码</h4>
                <p>本项目已经在Github上开源，欢迎大家下载围观</p>
                <a class="button button-block button-primary button-small" href="https://github.com/lzx2005/lzx2005rebuild" target="_blank">下载地址</a>
            </div>
        </div>

    </div>

</div> <!-- /container -->
<%@include file="../layout/footer.jsp" %>
</body>


<script type="text/javascript">
    $(function () {
        console.log("载入完毕");
        $.post("<%=basePath%>public/getAllBlogType",{
            a:1
        },function (data,status) {
            //2.填到select里面
            var list = data['data'];
            var blogTypeListDiv = $("#blogTypeListDiv");
            blogTypeListDiv.append('<p><a class="blog_type_a" href="<%=basePath%>">所有文章'+'</a></p>');
            for(var i=0;i<list.length;i++){
                var type = list[i];
                console.log(type['blogTypeId'],type['blogTypeName'],type['count']);
                if(type['blogTypeId']==0){
                    blogTypeListDiv.append('<p><a class="blog_type_a" href="<%=basePath%>?type='+type['blogTypeId']+'">默认('+type['count']+')'+'</a></p>');
                    continue;
                }
                blogTypeListDiv.append('<p><a class="blog_type_a"  href="<%=basePath%>?type='+type['blogTypeId']+'" >'+type['blogTypeName']+'('+type['count']+')'+'</a></p>');

            }
            setTimeout(function () {
                $("#loadingDiv").hide();
                blogTypeListDiv.show();
            }, 1000);
            console.log('加载完毕');
        });
    });
</script>
</html>