<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">

        <li <c:if test="${actionName=='/admin'}">class="active"</c:if>><a href="<%=basePath%>admin">后台首页 <span class="sr-only">(current)</span></a></li>
        <%--<li><a href="http://v3.bootcss.com/examples/dashboard/#"></a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/#">Analytics</a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/#">Export</a></li>--%>
    </ul>
    <ul class="nav nav-sidebar">
        <li <c:if test="${actionName=='/admin/blog'}">class="active"</c:if>><a href="<%=basePath%>admin/blog">博文管理</a></li>
        <li <c:if test="${actionName=='/admin/blog_add_markdown'}">class="active"</c:if>><a href="<%=basePath%>admin/blog_add_markdown">写文章</a></li>
        <%--<li><a href="http://v3.bootcss.com/examples/dashboard/">One more nav</a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/">Another nav item</a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/">More navigation</a></li>--%>
    </ul>
    <%--<ul class="nav nav-sidebar">
        <li><a href="http://v3.bootcss.com/examples/dashboard/">1Nav item again</a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/">One more nav</a></li>
        <li><a href="http://v3.bootcss.com/examples/dashboard/">Another nav item</a></li>
    </ul>--%>
</div>