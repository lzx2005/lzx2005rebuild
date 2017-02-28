var tool = {
    /**
     * 获得BaseUrl
     * @returns {string}
     */
    getBaseUrl: function () {
        var location = (window.location + '').split('/');
        var basePath = location[0] + '//' + location[2] + '/' + location[3];
        return basePath;
    },

    /**
     * 判断字符串是否不为空
     * @param text
     * @returns {boolean}
     */
    notNull: function (text) {
        text = $.trim(text);
        return !!(typeof(text) != "undefined" && text.length > 0);
    },

    /**
     * 判断一堆字符串是否不为空
     * @returns {boolean}
     */
    notNullAll: function () {
        for (var i = 0; i < arguments.length; i++) {
            var isNull = tool.isNull(arguments[i]);
            if (isNull) return false;
        }
        return true;
    },

    /**
     * 判断字符串是否为空
     * @param text
     * @returns {boolean}
     */
    isNull: function (text) {
        return !tool.notNull(text);
    }
}

var loginScript = {
    URL: {
        loginSubUrl: "/login_sub",
    },
    beforeSubmit: function (validate) {
        var username = $("#username").val();
        var password = $("#password").val();
        console.log(username);
        if (tool.notNullAll(username, password)) {
            //填写了账号和密码
            var md5password = hex_md5(password);
            loginScript.doSubmit(username, md5password,validate);
        }else{
            //todo 这边要好看点的弹窗
            alert("请填写账号密码");
        }
        return false;
    },
    doSubmit: function (username, md5password,validate) {
        var submitUrl = loginScript.URL.loginSubUrl;
        $.post(submitUrl, {
            username: username,
            password: md5password,
            geetest_challenge: validate.geetest_challenge,
            geetest_validate: validate.geetest_validate,
            geetest_seccode: validate.geetest_seccode
        },
        function (data) {
            if(data['success']){
                console.log("登陆成功");
                window.location.reload();
            }else{
                console.log("登陆失败");
                alert(data['msg']);
            }
        });
    },
    initCaptcha: function () {
        // 验证开始需要向网站主后台获取id，challenge，success（是否启用failback）
        $.ajax({
            url: "/captcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
            type: "get",
            dataType: "json",
            success: function (data) {
                // 使用initGeetest接口
                // 参数1：配置参数
                // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
                console.log(data);
                initGeetest({
                    gt: data.gt,
                    https: true,
                    challenge: data.challenge,
                    product: "popup", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                    offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                    // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
                }, loginScript.handlerPopup);
            }
        });
        return false;
    },
    handlerPopup : function (captchaObj) {
        // 成功的回调
        captchaObj.onSuccess(function () {
            var validate = captchaObj.getValidate();
            loginScript.beforeSubmit(validate);
        });
        $("#submitBtn").click(function () {

            var username = $("#username").val();
            var password = $("#password").val();

            if (tool.notNullAll(username, password)) {
                captchaObj.show();
            }else{
                //todo 这边要好看点的弹窗
                alert("请填写账号密码");
            }
        });
        // 将验证码加到id为captcha的元素里
        captchaObj.appendTo("#popup-captcha");
        // 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    }
}


var blogScript = {
    URL : {
        deleteUrl: "/admin_restful/blog/delete"
    },
    doDelete: function (blogId) {
        swal({
            title: "注意",
            text: "确定要删除当前文章吗？",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
            confirmButtonText: "删除",
            cancelButtonText: "取消",
        }, function () {
            $.ajax({
                url: blogScript.URL.deleteUrl,
                type: 'POST',
                data: {
                    blogId:blogId
                },
                success:function (data, textStatus) {
                    if(data['success']==true){
                        swal({
                            title: "成功！",
                            text: data['msg'],
                            type: "success"
                        },function () {
                            window.location.reload();
                        });
                    }else{

                        swal({
                            title: "失败！",
                            text: data['msg'],
                            type: "error"
                        },function () {
                            window.location.reload();
                        });
                    }
                },
                error:function (XMLHttpRequest, textStatus, errorThrown) {
                    swal("请求失败");
                }
            });
        });
    }
}



var messageScript = {
    URL : {
        messageSubmitUrl: "/public/message_submit"
    },
    doSubmitMessage: function () {
        var email = $("#email").val();
        var message = $("#message").val();
        var realUrl = messageScript.URL.messageSubmitUrl;
        $.ajax({
            url: realUrl,
            type: 'POST',
            data: {
                email:email,
                message:message
            },
            success:function (data, textStatus) {
                if(data['success']==true){
                    alert(data['msg']);
                }else{
                    alert(data['msg']);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                alert("请求失败");
            }
        });
        return false;
    }
}
