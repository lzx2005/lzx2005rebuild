<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${blogs.data.curPage >1 }">
    <a href="<%=basePath%>${pageActionName}?${urlParams}page=${blogs.data.curPage-1 }" class="button button-primary button-rounded button-small">上一页</a>
</c:if>
<c:if test="${blogs.data.curPage <=1 }">
    <a class="button button-primary button-rounded button-small disabled">上一页</a>
</c:if>

<c:if test="${blogs.data.curPage < blogs.data.totalPage }">
    <a href="<%=basePath%>${pageActionName}?${urlParams}page=${blogs.data.curPage+1 }" class="button button-primary button-rounded button-small pull-right">下一页</a>
</c:if>
<c:if test="${blogs.data.curPage >= blogs.data.totalPage }">
    <a class="button button-primary button-rounded button-small disabled pull-right">下一页</a>
</c:if>