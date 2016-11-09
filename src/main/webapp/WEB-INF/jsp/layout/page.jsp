<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${blogs.data.curPage >1 }">
    <a href="<%=basePath%>${pageActionName}?page=${blogs.data.curPage-1 }" class="btn btn-primary">上一页</a>
</c:if>
<c:if test="${blogs.data.curPage <=1 }">
    <a class="btn btn-primary disabled">上一页</a>
</c:if>

<c:if test="${blogs.data.curPage < blogs.data.totalPage }">
    <a href="<%=basePath%>${pageActionName}?page=${blogs.data.curPage+1 }" class="btn btn-primary pull-right">下一页</a>
</c:if>
<c:if test="${blogs.data.curPage >= blogs.data.totalPage }">
    <a class="btn btn-primary disabled pull-right">下一页</a>
</c:if>