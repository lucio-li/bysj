$(function() {
    /* 目录部分 begin*/
    $("#catalogSecondPart").hide();
    $("#libraryType").change(function() {//选择不同库名，则更改一级目录名
        $("#catalogSecondPart").hide();
        var library = $("#libraryType option:selected").val();
        //更改一级目录
        $.ajax({
            url: "../../knowledge/getCatalogFirst",
            data :{
                library: library
            },
            success: function(res) {
                var catalogFirst = res.catalogFirst;
                $("#catalogFirst").children("option").remove();
                var optionNode = "<option value=\"all\">请选择一级目录</option>";
                $("#catalogFirst").append(optionNode);
                for (var i = 0; i < catalogFirst.length; i++) {
                    var optionNode = "<option value=\"" + catalogFirst[i].code + "\">" + catalogFirst[i].name + "</option>";
                    $("#catalogFirst").append(optionNode);
                }
            }
        })
    })
    //点击一级目录
    $("#catalogFirst").change(function() {//选择不同一级目录，则更改二级目录名
        $("#catalogSecondPart").show();
        var library = $("#libraryType option:selected").val();
        var parent_code = $("#catalogFirst option:selected").val();
        //更改二级目录名
        $.ajax({
            url: "../../knowledge/getCatalogSecond",
            data :{
                library: library,
                parent_code: parent_code,
                level: 2
            },
            success: function(res) {
                var catalogSecond = res.catalogSecond;
                $("#catalogSecond").children("option").remove();
                var optionNode = "<option value=\"all\">请选择二级目录</option>";
                $("#catalogSecond").append(optionNode);
                for (var i = 0; i < catalogSecond.length; i++) {
                    var optionNode = "<option value=\"" + catalogSecond[i].code + "\" data-id=\"" +  catalogSecond[i].id + "\">" + catalogSecond[i].name + "</option>";
                    $("#catalogSecond").append(optionNode);
                }
            }
        })

    });
    /* 目录部分 end */

    /* 添加内容部分 begin */
    $("#applyBtn").click(function() {
        var catalogFirst = $("#catalogFirst option:selected").val();
        if (catalogFirst == "all") {
            $("#dialogText").text("请选择一级目录");
            $("#androidDialog1").fadeIn(200);
            $("#androidDialog1 .weui-dialog__btn_primary").attr("data-id", "catalogFirst");
            return;
        }
        var catalogSecond = $("#catalogSecond option:selected").val();
        if (catalogSecond == "all") {
            $("#dialogText").text("请选择二级目录");
            $("#androidDialog1").fadeIn(200);
            $("#androidDialog1 .weui-dialog__btn_primary").attr("data-id", "catalogSecond");
            return;
        }
        var catalog_id = $("#catalogSecond option:selected").attr("data-id");
        var titleText = $("#titleText").val();
        if (!titleText) {
            $("#dialogText").text("请输入标题");
            $("#androidDialog1").fadeIn(200);
            $("#androidDialog1 .weui-dialog__btn_primary").attr("data-id", "titleText");
            return;
        }
        var descriptionText = $("#descriptionText").val();
        var contentText = $("#contentText").val();
        if (!contentText) {
            $("#dialogText").text("请输入具体内容");
            $("#androidDialog1").fadeIn(200);
            $("#androidDialog1 .weui-dialog__btn_primary").attr("data-id", "contentText");
            return;
        }

        //申请人信息
        var username = $("#username").val();
        var phone  = $("#phone").val();
        var email = $("#email").val();
        $.ajax({
            url: "./newApply",
            data :{
                catalog_id:catalog_id,
                title: titleText,
                description:descriptionText,
                content: contentText,
                username:username,
                phone:phone,
                email:email
            },
            success: function(res) {
                if (res.result == "success") {
                    location.href = "../../admin/msgSuccess";
                } else {
                    location.href = "../../admin/msgWarn";
                }
            }
        })



    })
    $("#androidDialog1 .weui-dialog__btn_default").click(function() {
        $("#androidDialog1").fadeOut(200);
    })
    $("#androidDialog1 .weui-dialog__btn_primary").click(function() {
        $("#androidDialog1").fadeOut(200);
        var id = $(this).attr("data-id");
        $("#" + id).focus();
    })
    /* 添加内容部分 end */
})