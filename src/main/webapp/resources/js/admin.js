/**
 * Created by john on 2017/3/9.
 */


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


var blogTypeScript = {
    URL : {
        deleteUrl: "/admin_restful/blog_type/delete"
    },
    doDelete: function (blogTypeId) {
        swal({
            title: "注意",
            text: "确定要删除当前类型吗？",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
            confirmButtonText: "删除",
            cancelButtonText: "取消",
        }, function () {
            $.ajax({
                url: blogTypeScript.URL.deleteUrl,
                type: 'POST',
                data: {
                    blogTypeId:blogTypeId
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