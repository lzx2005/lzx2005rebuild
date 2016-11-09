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
        return (typeof(text) != "undefined" && text.length > 0) ? true : false;
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
    beforeSubmit: function () {
        var username = $("#username").val();
        var password = $("#password").val();
        console.log(username);
        if (tool.notNullAll(username, password)) {
            //填写了账号和密码
            var md5password = hex_md5(password);
            loginScript.doSubmit(username, md5password);
        }
        return false;
    },
    doSubmit: function (username, md5password) {
        var submitUrl = loginScript.URL.loginSubUrl;
        $.post(submitUrl, {
            username: username,
            password: md5password
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
    }
}


var blogScript = {
    URL : {
        deleteUrl: "/admin_restful/blog/delete"
    },
    doDelete: function (blogId) {
        var realUrl = blogScript.URL.deleteUrl;
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