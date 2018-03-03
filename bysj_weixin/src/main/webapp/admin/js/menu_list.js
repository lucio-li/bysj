$(function () {
    $(".addMenuBtn").click(function () {

        var level = location.search.substr(1).split("=")[1].slice(0, 1);//获取url中的level的参数
        if (level == 1) {
            var len = $("table tr").length;
            if (len >= 4) {
                alert("添加失败，一级菜单数量不能超过三个！");
                return;
            }

            $("#addMenu").show();
            location.href = "#add";
        } else {
            var len = $("table tr").length;

            $("#addMenuSecond").show();
            location.href = "#addSecond";
        }

    })
    //选择菜单类型事件
    $("#menuTypeAdd select").click(function (item) {
        var type = $("#menuTypeAdd option:selected").val();
        switch (type) {
            case "click"://发送消息
                $("#menu_url").hide();
                $("#menu_message").show();
                break;
            case "view"://跳转页面
                $("#menu_message").hide();
                $("#menu_url").show();
                break;
            default:
                $("#menu_url").hide();
                $("#menu_message").hide();
                break;
        }

    })

    //二级菜单，选择菜单类型事件
    $("#menuTypeAddSecond select").click(function (item) {
        var type = $("#menuTypeAddSecond option:selected").val();
        switch (type) {
            case "click"://发送消息
                $("#menu_urlSecond").hide();
                $("#menu_messageSecond").show();
                break;
            case "view"://跳转页面
                $("#menu_messageSecond").hide();
                $("#menu_urlSecond").show();
                $("#selectArticleSecondPart").hide();
                $("#selectImageSecondPart").hide();
                break;
            default:
                $("#menu_urlSecond").hide();
                $("#menu_messageSecond").hide();
                break;
        }

    })

    //新增一级菜单时选择消息类型的点击事件
    $("#menu_message select").click(function() {
        var type = $("#menu_message option:selected").val();
        switch (type) {
            case "image"://图片消息
                $("#selectArticleFirstPart").hide();
                $("#selectImageFirstPart").show();
                break;
            case "article"://图文消息
                $("#selectImageFirstPart").hide();
                $("#selectArticleFirstPart").show();
                break;
            default:
                $("#selectArticleFirstPart").hide();
                $("#selectImageFirstPart").hide();
                break;
        }
    })


    //新增一级菜单时点击提交
    $("#submit").click(function () {
        var level = location.search.substr(1).split("=")[1].slice(0, 1);//获取url中的level的参数
        if (level == 1) {
            addFirstMenu(level)
        } else {
            addSeondMenu(level)
        }

    })
    //删除菜单点击事件
    $(".deleteBtn").click(function (item) {
        var id = $(this).attr("menu_id");//菜单的id
        var index = $(this).attr("index");//列表索引，从1开始
        var text = $(".button_name").eq(1).text();
        if (confirm("确定删除菜单名为\"" + $(".button_name").eq(index - 1).text() + "\"的菜单按钮吗")) {
            $.ajax({
                url: "./deleteButton",
                data :{
                    id:id
                },
                success: function(res) {
                    alert(res.msg);//
                    var level = location.search.substr(1).split("=")[1].slice(0, 1);//获取url中的level的参数
                    location.href = "./list?level=" + level;
                }
            })
        }
    })




    //新增一级菜单
    function addFirstMenu(level) {
        var name = $("#menuName").val();
        if (!name) {
            $("#menuName").focus();
            return;
        }
        var type = $("#menuTypeAdd option:selected").val();
        if (type != "click" && type != "view") {
            alert("请选择菜单类型类型");
            $("#menuTypeAdd select").focus();
            return;
        }

        // var len = $("table tr").length;
        // if (len >= 4) {
        //     alert("添加失败，一级菜单数量不能超过三个！");
        //     return;
        // }
        var msg_type = $("#menu_message option:selected").val();
        if (msg_type == 0 && type == "click") {
            alert("请选择消息类型");
            $("#menu_message select").focus();
            return;
        }
        if (msg_type == "image") {
            var key = $("#selectImageFirst option:selected").val();
        } else {
            var key = $("#selectArticleFirstPart option:selected").val();
        }
        //页面url
        var url = $("#menu_url input").val();
        $.ajax({
            url: "./addButton",
            data :{
                name: name,
                type: type,
                level: level,
                key: key,
                url:url,
                msg_type:msg_type
            },
            success: function(res) {
                if (res.result == "success") {
                    alert("新增菜单成功");
                } else {
                    alert("新增菜单失败，" + res.msg);
                }
                var level = location.search.substr(1).split("=")[1].slice(0, 1);//获取url中的level的参数
                location.href = "./list?level=" + level;
            }
        })
    }


    // //显示新增二级菜单的部分
    // $("#addMenuBtnSecond").click(function () {
    //     var len = $("table tr").length;
    //     if (len >= 4) {
    //         alert("添加失败，一级菜单数量不能超过三个！");
    //         return;
    //     }
    //     $("#addMenuSecond").show();
    //     location.href = "#addSecond";
    // })

    //新增二级菜单
    function addSecondMenu(level) {
        var name = $("#menuNameSecond").val();
        if (!name) {
            alert("请输入菜单名")
            $("#menuNameSecond").focus();
            return;
        }
        var parentId = $("#parentNameSecond option:selected").val();
        if (parentId == 0) {
            alert("请选择父菜单");
            $("#parentNameSecond select").focus();
            return;
        }

        var type = $("#menuTypeAddSecond option:selected").val();
        if (type != "click" && type != "view") {
            alert("请选择菜单类型");
            $("#menuTypeAddSecond select").focus();
            return;
        }
        var msg_type = $("#menu_messageSecond option:selected").val();
        if (msg_type == 0 && type == "click") {
            alert("请选择消息类型");
            $("#menu_messageSecond select").focus();
            return;
        }
        var key = $("#selectImageSecond option:selected").val();

        // var len = $("table tr").length;
        // if (len >= 4) {
        //     alert("添加失败，一级菜单数量不能超过三个！");
        //     return;
        // }
        //页面url
        var url = $("#menu_urlSecond input").val();
        $.ajax({
            url: "./addButton",
            data :{
                name:name,
                type: type,
                level:level,
                parent_id: parentId,
                key:key,
                url:url,
                msg_type:msg_type
            },
            success: function(res) {
                if (res.result == "success") {
                    alert("新增菜单成功");
                } else {
                    alert("新增菜单失败，" + res.msg);
                }
                var level = location.search.substr(1).split("=")[1].slice(0, 1);//获取url中的level的参数
                location.href = "./list?level=" + level;
            }
        })
    }
    //新增一级菜单时选择图片后把对应的图片显示出来
    $("#selectImageFirst select").click(function() {
        var media_id = $("#selectImageFirst option:selected").val();
        var url = $("#selectImageFirst option:selected").attr("data-url");
        if (media_id != 0) {
            $("#coverPicImageFirst").attr("src", "../image/getImg?url=" + url);
            $("#coverPicImageFirst").show();
        } else {
            $("#coverPicImageFirst").attr("src", "");
            $("#coverPicImageFirst").hide();
        }

        //console.log(media_id)
    })
    //新增二级菜单时选择图片后把对应的图片显示出来
    $("#selectImageSecond select").click(function() {
        var media_id = $("#selectImageSecond option:selected").val();
        var url = $("#selectImageSecond option:selected").attr("data-url");
        if (media_id != 0) {
            $("#coverPic").attr("src", "../image/getImg?url=" + url);
            $("#coverPic").show();
        } else {
            $("#coverPic").attr("src", "");
            $("#coverPic").hide();
        }

        //console.log(media_id)
    })
    //新增一级菜单时选择图文后把对应的图片显示出来
    $("#selectArticleFirst select").click(function() {
        var media_id = $("#selectArticleFirst option:selected").val();
        var url = $("#selectArticleFirst option:selected").attr("data-url");
        if (media_id != 0) {
            $("#coverPicArticleFirst").attr("src", "../image/getImg?url=" + url);
            $("#coverPicArticleFirst").show();
        } else {
            $("#coverPicArticleFirst").attr("src", "");
            $("#coverPicArticleFirst").hide();
        }

        //console.log(media_id)
    })
    //新增二级菜单时选择图文后把对应的图片显示出来
    $("#selectArticleSecond select").click(function() {
        var media_id = $("#selectArticleSecond option:selected").val();
        var url = $("#selectArticleSecond option:selected").attr("data-url");
        if (media_id != 0) {
            $("#coverPicArticleSecond").attr("src", "../image/getImg?url=" + url);
            $("#coverPicArticleSecond").show();
        } else {
            $("#coverPicArticleSecond").attr("src", "");
            $("#coverPicArticleSecond").hide();
        }

        //console.log(media_id)
    })
    //新增二级菜单时点击提交
    $("#submitSecond").click(function () {

        addSecondMenu(2)

    })
    //新增二级菜单时选择消息类型的点击事件
    $("#menu_messageSecond select").click(function() {
        var type = $("#menu_messageSecond option:selected").val();

        switch (type) {
            case "image"://图片消息
                $("#selectArticleSecondPart").hide();
                $("#selectImageSecondPart").show();
                break;
            case "article"://图文消息
                $("#selectImageSecondPart").hide();
                $("#selectArticleSecondPart").show();
                break;
            default:
                $("#selectArticleSecondPart").hide();
                $("#selectImageSecondPart").hide();
                break;
        }
    })

})