<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <title>文章类型管理</title>

    <%@include file="../layout/css.jsp"%>
    <%@include file="../layout/script.jsp"%>
</head>

<body>
<%@include file="../layout/nav.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../layout/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h2 class="sub-header">Blog Type List</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>类型</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td>-</td>
                        <td>-</td>
                        <td>-</td>
                        <td><button class="btn btn-primary" onclick="blogTypeScript.doCreate()">添加</button></td>
                    </tr>
                    <c:forEach var="item" items="${blogtypes.data }" varStatus="status">
                        <tr>
                            <td>${item.blogTypeId }</td>
                            <td>${item.blogTypeName }</td>
                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><button class="btn btn-primary" onclick="blogTypeScript.doEdit(${item.blogTypeId },'${item.blogTypeName }')">编辑</button>&nbsp;<button class="btn btn-danger" onclick="blogTypeScript.doDelete(${item.blogTypeId })">删除</button></td>
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