$(function() {
    $(".verifyPartBtn").click(function() {
        var id = $(this).attr("data-id");
        $.ajax({
            url: "./getApply",
            data :{
                id:id
            },
            success: function(res) {
                var content = res.content;
                $("#verifyPart").show();
                $("#applyPath").val(content.path);
                $("#titleText").val(content.title);
                $("#descriptionText").val(content.description);
                $("#contentText").val(content.content);
                $("#catalogId").val(content.catalog_id);
                $("#applyId").val(content.id);
                if (res.applyUser) {
                    var applyUser = res.applyUser;
                    if (!applyUser.username) {
                        $("#username").val("未知");
                    } else {
                        $("#username").val(applyUser.username);
                    }
                    if (!applyUser.phone) {
                        $("#phone").val("未知");
                    } else {
                        $("#phone").val(applyUser.phone);
                    }
                    if (!applyUser.email) {
                        $("#email").val("未知");
                    } else {
                        $("#email").val(applyUser.email);
                    }
                } else {
                    $("#username").val("未知");
                    $("#phone").val("未知");
                    $("#email").val("未知");
                }
            }
        })
    })


    //通过审核
    $("#passBtn").click(function() {
        var title = $("#titleText").val();
        var description = $("#descriptionText").val();
        var  content = $("#contentText").val();
        var catalogId = $("#catalogId").val();
        var applyId = $("#applyId").val();
        $.ajax({
            url: "./pass",
            data :{
                title:title,
                description:description,
                content:content,
                catalog_id: catalogId,
                applyId:applyId

            },
            success: function(res) {
                alert(res.result);
            }
        })
    })
    //不通过审核
    $("#refuseBtn").click(function() {
        var applyId = $("#applyId").val();
        $.ajax({
            url: "./refuse",
            data :{
                applyId:applyId
            },
            success: function(res) {
                alert(res.result);
            }
        })
    })
})