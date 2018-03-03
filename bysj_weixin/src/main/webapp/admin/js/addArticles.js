/**
 * Created by lq on 2018/2/19.
 */
$(function() {




    $("#selectImage select").click(function() {
        var media_id = $("#selectImage option:selected").val();
        var url = $("#selectImage option:selected").attr("data-url");
        if (media_id != 0) {
            $("#coverPic").attr("src", "../image/getImg?url=" + url);
            $("#coverPic").show();
        } else {
            $("#coverPic").attr("src", "");
            $("#coverPic").hide();
        }

        //console.log(media_id)
    })
    $("#submitBtn").click(function() {
        var title = $("#title").val();
        var media_id = $("#selectImage option:selected").val();
        if (media_id == 0) {
            alert("请先选择图片");
            return;
        }
        var content = $("#content").val();
        $.ajax({
            url: "../articles/add",
            data :{
                title:title,
                thumb_media_id:  media_id,
                content: content
            },
            success: function(res) {
                if (res.msg == success) {
                    alert("新增成功");
                    location.href = "./addArticles";//刷新页面
                } else {
                    alert("操作失败，请稍后重试");
                    location.href = "./addArticles";//刷新页面
                }
                alert(res.msg);
            }
        })
    })

    //添加图文素材
    $(".showAddPart").click(function() {
        $("#addPart").show();
        location.href = "#add";
    })


})
