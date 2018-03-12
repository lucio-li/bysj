/**
 * Created by lq on 2018/2/19.
 */
$(function() {
    $("#submitBtn").click(function() {
        var titleText = $("#titleText").val();
        if (!titleText) {
            alert("请先输入公告标题");
            $("#titleText").focus();
            return;
        }
        var authorText = $("#authorText").val();
        var contentText = $("#contentText").val();
        if (!contentText) {
            alert("请先输入公告内容");
            $("#contentText").focus();
            return;
        }
        $.ajax({
            url: "./add",
            data :{
                title:titleText,
                author:  authorText,
                content: contentText
            },
            success: function(res) {
                if (res.result == "success") {
                    alert("更改成功");
                    location.href = "./list";//刷新页面
                } else {
                    alert("操作失败，请稍后重试");
                    location.href = "./list";//刷新页面
                }
            }
        })
    })


})
