<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="jumbotron footer footer1">

    <div class="container footer-container">
        <div class="row">
            <div class="col-md-4">
                <h4>About Me</h4>
                <hr/>
                三年Java Web在校外包经验，使用过Spring，Spring MVC，Mybatis，Jfinal等开源框架，使用过ActiveMQ消息队列、Ehcache缓存框架，可以设计Restful接口等，前端方面使用过Bootstrap等。
            </div>

            <div class="col-md-4">
                <h4>Social Network</h4>
                <hr/>
                <ul>
                    <li>微博：<a href="http://weibo.com/u/2557929062" target="_blank">lzx2005</a></li>
                    <li>知乎：<a href="https://www.zhihu.com/people/lzx2005" target="_blank">知乎账号</a></li>
                    <li>GitHub：<a href="http://github.com/lzx2005" target="_blank">lzx2005</a></li>
                    <li>邮箱：<a href="mailto:978744976@qq.com">邮箱地址</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h4>Contact Me</h4>
                <hr/>
                <form method="post">
                    <div class="form-group">
                        <input type="email" placeholder="邮箱" id="email" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="留言" id="message" name="message" class="form-control">
                    </div>
                    <div class="form-group">
                        <a class="button button-block button-primary button-small" onclick="messageScript.doSubmitMessage()">给我留言</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</footer>

<footer class="jumbotron footer footer2">

    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center end-text">
                Raven 2016 浙ICP备16002440号
            </div>
        </div>
    </div>
</footer>