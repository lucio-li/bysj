$(function() {
    //修改用户名的点击事件
    $(".updateBtn").click(function() {
        var openid = $(this).attr("data-id");
        $.ajax({
            url: "./userInfo",
            data :{
                openid:openid
            },
            success: function(res) {
                $("#update").show();
                $("#wxNameText").val(res.nickname);
                $("#updateImgUrl").attr("src", res.headimgurl);
                $("#username").val(res.username);
                $("#userOpenid").val(openid);
            }
        })
    });

    //确定修改的点击事件
    $("#passBtn").click(function() {
        var openid = $("#userOpenid").val();
        $.ajax({
            url: "./updateInfo",
            data :{
                openid:openid,
                username: $("#username").val()
            },
            success: function(res) {
                if (res.result == "succcess") {
                    alert("修改成功")
                } else {
                    alert("修改失败，请稍后重试");
                }
                location.href = "./userList";//刷新页面
            }
        })
    });
    //取消修改用户信息
    $("#refuseBtn").click(function() {
        $("#update").hide();
        location.href = "#header";
    });

    //删除用户
    $(".deleteBtn").click(function() {
        if(confirm("请确认是否删除微信名为" + $(this).attr("data-nickname") + "的用户")) {
            $.ajax({
                url: "./deleteUser",
                data :{
                    openid:openid
                },
                success: function(res) {
                    if (res.result == "succcess") {
                        alert("修改成功")
                    } else {
                        alert("修改失败，请稍后重试");
                    }
                    location.href = "./userList";//刷新页面
                }
            })
        }
    })
})