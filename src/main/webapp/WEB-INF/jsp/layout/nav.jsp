<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-custom">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=basePath %>">Raven的笔记本</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <form class="navbar-form navbar-right" method="post" onSubmit="return false">
                        <div class="form-group">
                            <input type="text" placeholder="用户名" id="username" name="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="密码" id="password" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <button type="button" class="button button-primary button-small" id="submitBtn">登录</button>
                        </div>
                        <div id="popup-captcha"></div>
                    </form>
                </c:when>
                <c:otherwise>
                    <span class="pull-right">
                        <a class="navbar-brand" href="<%=basePath %>admin">${sessionScope.user.username}</a>
                        <a class="navbar-brand" href="<%=basePath %>log_out">退出登录</a>
                    </span>
                </c:otherwise>
            </c:choose>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <script type="text/javascript">
            $(function () {
                console.log("未登陆");
                loginScript.initCaptcha();
            });
        </script>
    </c:when>
</c:choose>
