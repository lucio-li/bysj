$(function() {
    var time;
    $(".commentBtn").click(function() {
        $(".box_write").show();
        time = $(this).attr("data-time");
        $(".commentInputFooter").focus();
    });
    // $(".box_write").blur(function() {
    //     $(".box_write").hide();
    // });

    //发布评论
    $("#sendComment").click(function() {
        if (!$(".commentInputFooter").val()) {
            alert("评论内容不能为空");
            $(".commentInputFooter").focus();
            return;
        }
        var content = $(".commentInputFooter").val();
        $.ajax({
            url: '../comments/add', //仅为示例，并非真实的接口地址
            //url: 'http://127.0.0.1:8089/lili/upload/content',
            data: {
                momentsTime: time,
                contentDetail: content,
                name: $("#nickname").val()
            },
            success: function (res) {
                alert("评论成功")
                //成功后刷新叶苗
                location.href = "./list";
            },
            error: function (e) {
                console.log("评论失败")
                console.log(e)
            }
        })
    })
})