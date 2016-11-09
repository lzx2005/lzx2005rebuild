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

    <title>${blog.title } - Raven的笔记本</title>
    <base href="<%=basePath %>"/>
    <%@include file="../layout/css.jsp" %>
    <%@include file="../layout/script.jsp" %>
</head>

<body>


<%@include file="../layout/nav.jsp" %>


<div class="container top-60">
    <div class="row">
        <div class="col-md-12">
            <div class="row" id="content">
                <c:if test="${blog.markdown == 1}">
                    载入中...
                </c:if>
                <c:if test="${blog.markdown == 0}">
                    ${blog.content }
                </c:if>
            </div>
        </div>
    </div>


</div> <!-- /container -->
<textarea id="markdownsource" class="hidden">
    ${blog.content }
</textarea>

<%@include file="../layout/footer.jsp" %>
<script type="text/javascript">
    $(function () {
        console.log("载入完毕");
        //loginScript.beforeSubmit();
    });
</script>

<c:if test="${blog.markdown == 1}">
<script src="//cdn.bootcss.com/remarkable/1.7.1/remarkable.min.js"></script>
<script src="//cdn.bootcss.com/highlight.js/9.7.0/highlight.min.js"></script>
<script type="text/javascript">
    function Editor(input, preview) {
        this.update = function () {
            var str = input.value.trim();
            console.log(str)
            var md = new Remarkable();
            preview.innerHTML = md.render(str);
        };
        input.editor = this;
        this.update();
    }
    new Editor(document.getElementById("markdownsource"), document.getElementById("content"));
    hljs.initHighlightingOnLoad();
</script>
</c:if>
</body>
</html>