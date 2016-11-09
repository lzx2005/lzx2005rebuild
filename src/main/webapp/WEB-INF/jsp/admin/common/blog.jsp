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

    <title>后台主页</title>

    <%@include file="../layout/css.jsp"%>
    <%@include file="../layout/script.jsp"%>
</head>

<body>
<%@include file="../layout/nav.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../layout/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h2 class="sub-header">Blog List</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>标题</th>
                        <th>作者</th>
                        <th>类型</th>
                        <th>是否为Markdown语言</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="item" items="${blogs.data.list }" varStatus="status">
                        <tr>
                            <td>${item.blogId }</td>
                            <td>${item.title }</td>
                            <td>${item.author }</td>
                            <td>${item.blogType }</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.markdown == 1}">是</c:when>
                                    <c:otherwise>否</c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="<%=basePath%>admin/blog_edit?blog_id=${item.blogId }" class="btn btn-primary">编辑</a>&nbsp;<button class="btn btn-danger" onclick="blogScript.doDelete(${item.blogId })">删除</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
            <%@include file="../../layout/page.jsp"%>
        </div>
    </div>
</div>

</body></html>