<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Raven的笔记本</title>
    <base href="<%=basePath %>"/>
    <%@include file="../layout/css.jsp" %>
    <%@include file="../layout/script.jsp" %>
</head>

<body>

<%@include file="../layout/nav.jsp" %>

<div class="jumbotron jumb-block">
    <div class="container">
        <h1>Hello!</h1>
        <p>三年Java Web在校外包经验，使用过Spring，Spring MVC，Mybatis，Jfinal等开源框架，使用过ActiveMQ消息队列、Ehcache缓存框架，可以设计Restful接口等，前端方面使用过Bootstrap等。</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">简历 »</a></p>
    </div>
</div>

<div class="container">

    <div class="row">

        <div class="col-md-8">

            <div class="row">

                <c:forEach var="item" items="${blogs.data.list }" varStatus="status">
                    <div class="col-md-12 blog-block">
                        <h2>${item.title }</h2>
                        <div class="font1">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${item.author }
                            <span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd hh:MM:ss"/> </span>
                        </div>
                        <p>${item.description }</p>
                        <p>
                            <a class="btn btn-default" href="<%=basePath%>blog/${item.blogId}/overview" role="button">View details »</a>
                            <%--<a class="btn btn-primary" href="#" role="button">See More</a>
                            <a class="btn btn-danger" href="#" role="button">Delete</a>--%>
                        </p>
                    </div>
                </c:forEach>
                <div class="col-md-12 blog-block-page">
                    <%@include file="../layout/page.jsp"%>
                </div>
            </div>


        </div>
        <div class="col-md-4">
            <div class="my-info">
                <div class="text-center">
                    <img class="my-avatar" src="<%=basePath%>resources/image/avatar.png">
                </div>
                <div>
                    <p>姓名:Raven</p>
                    <p>年龄:23</p>
                    <p>性别:男</p>
                    <p>邮箱:<a href="mailto:crow2005@vip.qq.com">crow2005@vip.qq.com</a></p>
                    <p>微博:<a href="http://weibo.com/u/2557929062">lzx2005</a></p>

                </div>
            </div>
        </div>

    </div>


</div> <!-- /container -->

<%@include file="../layout/footer.jsp" %>
<script type="text/javascript">
    $(function () {
        console.log("载入完毕");
        //loginScript.beforeSubmit();
    });
</script>
</body>
</html>