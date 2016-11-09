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

    <title>添加一篇文章</title>

    <%@include file="../layout/css.jsp"%>
    <%@include file="../layout/script.jsp"%>
</head>

<body>
<%@include file="../layout/nav.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="../layout/menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">添加一篇文章</h1>
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">标题：</label>
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="title" placeholder="请输入标题" name="title">
                    </div>
                </div>
                <%--<div class="form-group">
                    <label for="inputPassword3" class="col-sm-1 control-label">描述：</label>
                    <div class="col-sm-11">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入表述">
                    </div>
                </div>--%>
                <div class="form-group">
                    <label class="col-sm-1 control-label">博客正文：</label>
                    <div class="col-sm-11">
                        <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-1 col-sm-11">
                        <button type="submit" class="btn btn-primary btn-block">发布</button>
                    </div>
                </div>
            </form>
            <form action="<%=basePath%>admin_restful/image/upload" id="uploadForm" enctype="multipart/form-data" method="post">
                <input id="fileInput" type="file" style="display: none" name="fileInput" onchange="addPic()">
            </form>

            <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/tool/ueditor/ueditor.config.js"></script>
            <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/tool/ueditor/ueditor.all.min.js"> </script>
            <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/tool/ueditor/lang/zh-cn/zh-cn.js"></script>

            <script type="text/javascript" charset="utf-8" src="<%=basePath%>resources/tool/ueditor/jquery.form.js"></script>
            <script type="text/javascript">
                //实例化编辑器
                //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                UE.registerUI('上传图片', function(editor, uiName) {
                    //注册按钮执行时的command命令，使用命令默认就会带有回退操作
                    editor.registerCommand(uiName, {
                        execCommand: function() {
                            alert('execCommand:' + uiName)
                        }
                    });
                    //创建一个button
                    var btn = new UE.ui.Button({
                        //按钮的名字
                        name: uiName,
                        //提示
                        title: uiName,
                        //添加额外样式，指定icon图标，这里默认使用一个重复的icon
                        cssRules: 'background-position: -381px 0;',
                        //点击时执行的命令
                        onclick: function() {
                            openWindow();
                        }
                    });
                    //当点到编辑内容上时，按钮要做的状态反射
                    editor.addListener('selectionchange', function() {
                        var state = editor.queryCommandState(uiName);
                        if (state == -1) {
                            btn.setDisabled(true);
                            btn.setChecked(false);
                        } else {
                            btn.setDisabled(false);
                            btn.setChecked(state);
                        }
                    });
                    //因为你是添加button,所以需要返回这个button
                    return btn;
                });


                var ue = UE.getEditor('editor');
                function openWindow(){
                    var fileInput = document.getElementById("fileInput");//隐藏的file文本ID
                    fileInput.click();//加一个触发事件
                }

                function addPic(){
                    //var fileInput = document.getElementById("fileInput");
                    //隐藏的file文本ID
                    if ($("#fileInput").val() == "") {
                        alert("请选择一个图片文件，再点击上传。");
                        return false;
                    }
                    console.log($('#uploadForm'));
                    $('#uploadForm').ajaxSubmit({
                        error : function(XmlHttpRequest, textStatus, errorThrown) {
                            alert("上传失败，请重新上传");
                        },
                        success: function (html, status) {
                            console.log(html);

                            if(status==""||html['success']==false){
                                alert("上传失败，请重新上传,"+html['msg']);
                            }else if(html=="default.jpg"){
                                alert("上传的不是图片，请重新上传");
                            }else{
                                var iid = html['msg'];
                                UE.getEditor('editor').setContent("<img id='"+iid+"' src='"+html['data']+"'>", true);
                            }
                        }
                    });
                }

                function saveNews() {
                    var title = $("#title").val();
                    var desc = $("#desc").val();
                    var city = $("#ddlCity").val();
                    var type = $("#type").val();

                    var range = UE.getEditor('editor').selection.getRange();
                    range.select();
                    var content_text = UE.getEditor('editor').getContentTxt();
                    var content = UE.getEditor('editor').getContent();
                    console.log(title);
                    console.log(desc);
                    console.log(content);
                    console.log(content_text);
                    if(title==""){
                        alert("请填写新闻标题");
                    }else if(desc==""){
                        alert("请填写新闻描述");
                    }else if(content==""){
                        alert("请填写新闻内容");
                    }else{
                        $.post("/backapi/saveNews", {
                            title : title,
                            desc : desc,
                            content : content,
                            city : city,
                            type: type,
                            content_text : content_text,
                        }, function(data, status) {
                            if(data=="true"){
                                alert("新闻\""+title+"\"保存成功！");
                                window.location.reload();
                            }else if(data=="login"){
                                alert("请登录");
                                window.location.href="/backlogin";
                            }else{
                                alert("图文消息\""+title+"\"保存失败！");
                            }
                        });
                    }
                }


            </script>
        </div>
    </div>
</div>

</body></html>