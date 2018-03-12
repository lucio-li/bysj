$(function() {
    $("#catalogSecond").hide();
    $("#libraryType").change(function() {//选择不同库名，则更改一级目录名
        $("#catalogSecond").hide();
        $("#addPart").hide();
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
                var optionNode = "<option value=\"all\">一级目录</option>";
                $("#catalogFirst").append(optionNode);
                for (var i = 0; i < catalogFirst.length; i++) {
                    var optionNode = "<option value=\"" + catalogFirst[i].code + "\">" + catalogFirst[i].name + "</option>";
                    $("#catalogFirst").append(optionNode);
                }
            }
        })

        //更改库下的内容
        $.ajax({
            url: "../../knowledge/getContentByLibrary",
            data :{
                library: library
            },
            success: function(res) {
                var contentList = res.contentList;
                loadByContentList(contentList);


            }
        })

    })
    //点击一级目录
    $("#catalogFirst").change(function() {//选择不同一级目录，则更改二级目录名
        $("#catalogSecond").show();
        $("#addPart").hide();
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
                var optionNode = "<option value=\"all\">二级目录</option>";
                $("#catalogSecond").append(optionNode);
                for (var i = 0; i < catalogSecond.length; i++) {
                    var optionNode = "<option value=\"" + catalogSecond[i].code + "\">" + catalogSecond[i].name + "</option>";
                    $("#catalogSecond").append(optionNode);
                }
            }
        })
        //更改目录下的内容

        if (parent_code == "all") {
            //点击全部
            $("#catalogSecondPart").hide();
            //更改库下的内容
            $.ajax({
                url: "../../knowledge/getContentByLibrary",
                data :{
                    library: library
                },
                success: function(res) {
                    var contentList = res.contentList;
                    loadByContentList(contentList);

                }
            })
        } else {
            $.ajax({
                url: "../../knowledge/getContentFirst",
                data :{
                    library: library,
                    code: parent_code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    loadByContentList(contentList)

                }
            })
        }

    });
    //点击二级目录
    $("#catalogSecond").change(function() {
        $("#addPart").hide();
        var library = $("#libraryType option:selected").val();
        var parent_code = $("#catalogFirst option:selected").val();
        var code = $("#catalogSecond option:selected").val();
        if (code == "all") {//二级目录的全部
            $.ajax({
                url: "../../knowledge/getContentFirst",
                data :{
                    library: library,
                    code: parent_code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    loadByContentList(contentList)

                }
            })
        } else {
            $.ajax({
                url: "../../knowledge/getContentSecond",
                data :{
                    library: library,
                    code: code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    loadByContentList(contentList)

                }
            })
        }
    })

    //显示添加内容部分
    $(".addContentBtn").click(function() {
        $("#addPart").show();
        location.href = "#addPart";
    })

    //添加具体内容的list进dom
    function loadContentList(contentList, contentId) {
        $("#" + contentId).children().remove();
        for (var i = 0; i < contentList.length; i++) {
            var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
            var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
            var spanNode = $("<span class=\"weui-cell__ft\"></span>");
            var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

            $("#" + contentId).append(aNode);
        }
    }

    function loadByContentList(contentList) {
        $("#contentListPart .contentList").remove();
        for (var i = 0; i < contentList.length; i++) {
            var trNode = $("<tr class=\"contentList\"></tr>");
            var numberTd = $("<td></td>").text(i + 1);
            var titleTd = $("<td></td>").text(contentList[i].title);
            var pathTd = $("<td></td>").text(contentList[i].path);
            var descriptionTd = $("<td></td>").text(contentList[i].description);
            var buttonTd = $("<td></td>");
            var buttonDiv  = $("<div class=\"button-group\"></div>");
            var aNode = $("<a class=\"deleteBtn button border-red\"  data-id=\"" + contentList[i].id + "\"><span class=\"icon-trash-o\"></span> 删除</a>");
            buttonTd.append(buttonDiv, aNode);
            trNode.append(numberTd, titleTd, pathTd,  descriptionTd, buttonTd);
            $("#contentListPart").append(trNode);
        }

    }


    //删除某一个知识内容
    $("#contentListPart").delegate(".deleteBtn", "click", function() {
        var id = $(this).attr("data-id");
        if (confirm("是否删除该内容")) {
            $.ajax({
                url: "./delete",
                data :{
                    id:id
                },
                success: function(res) {
                    var result = res.result;
                    location.href = "../tips?url=./knowledge/list";//刷新页面
                },
                error: function(res) {
                    location.href = "../tips?url=./knowledge/list";
                }
            })
        }
    })

    /*  添加内容部分 begin*/
    //选择库后，显示一级目录
    $("#selectLibraryAdd select").change(function () {

        var library = $("#selectLibraryAdd option:selected").val();
        if (library == 0) {
            $("#addForm .catalogFirst").hide();
            $("#addForm .catalogSecond").hide();
            return;
        }
        //更改一级目录
        $.ajax({
            url: "../../knowledge/getCatalogFirst",
            data :{
                library: library
            },
            success: function(res) {
                $("#addForm .catalogFirst").show();//显示一级目录
                var catalogFirst = res.catalogFirst;
                $("#catalogFirstAdd").children("option").remove();
                var optionNode = "<option value=\"0\">一级目录</option>";
                $("#catalogFirstAdd").append(optionNode);
                for (var i = 0; i < catalogFirst.length; i++) {
                    var optionNode = "<option value=\"" + catalogFirst[i].code + "\">" + catalogFirst[i].name + "</option>";
                    $("#catalogFirstAdd").append(optionNode);
                }
            }
        })
    })
    //选择一级目录后，显示二级目录
    $("#catalogFirstAdd").change(function() {//选择不同一级目录，则更改二级目录名
        var parent_code = $("#catalogFirstAdd option:selected").val();
        if (parent_code == 0) {
            $("#addForm .catalogSecond").hide();
            return;
        }
        $("#addForm .catalogSecond").show();

        var library = $("#selectLibraryAdd option:selected").val();

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
                $("#catalogSecondAdd").children("option").remove();
                var optionNode = "<option value=\"0\">请选择二级目录</option>";
                $("#catalogSecondAdd").append(optionNode);
                for (var i = 0; i < catalogSecond.length; i++) {
                    var optionNode = "<option value=\"" + catalogSecond[i].id + "\">" + catalogSecond[i].name + "</option>";
                    $("#catalogSecondAdd").append(optionNode);
                }
            }
        })

    });

    $("#submitBtn").click(function() {
        var library = $("#selectLibraryAdd option:selected").val();
        if (library == 0) {
            alert("请先选择库类别");
            $("#selectLibraryAdd select").focus();
            return;
        }
        var catalogFirst = $("#catalogFirstAdd option:selected").val();
        if (catalogFirst == 0) {
            alert("请先选择一级目录");
            $("#catalogFirstAdd").focus();
            return;
        }
        var catalogId = $("#catalogSecondAdd option:selected").val();
        if (catalogId == 0) {
            alert("请先选择二级目录");
            $("#catalogSecondAdd").focus();
            return;
        }
        var title = $("#titleText").val();
        if (!title) {
            alert("请输入标题");
            $("#titleText").focus();
            return;
        }
        var description = $("#descriptionText").val();

        var content = $("#contentText").val();
        if (!content) {
            alert("请先输入内容");
            $("#contentText").focus();
            return;
        }

        var fd = new FormData();
        fd.append("title", title);
        fd.append("description", description);
        fd.append("content", content);
        fd.append("catalog_id", catalogId);
        var file = document.getElementById("fileBtn").files[0];
        if (!file) {
            $.ajax({
                url: "./add",
                type: "POST",
                data :fd,
                processData:false,// 告诉jQuery不要去处理发送的数据
                contentType:false,// 告诉jQuery不要去设置Content-Type请求头
                success: function(res) {
                    var result = res.result;
                    location.href = "../tips?url=./knowledge/list";//刷新页面
                }
            })
        } else {

            fd.append("file", file);

            $.ajax({
                url: "./addFile",
                type: "POST",
                data :fd,
                processData:false,// 告诉jQuery不要去处理发送的数据
                contentType:false,// 告诉jQuery不要去设置Content-Type请求头
                success: function(res) {
                    var result = res.result;
                    location.href = "../tips?url=./knowledge/list";//刷新页面
                }
            })
        }


    })
    $("#selectFile").click(function() {
        $("#fileBtn").click();
    })
    $("#fileBtn").change(function () {
        var file = document.getElementById("fileBtn").files[0];
        if(file.type != "application/pdf") {
            alert("请确保文件为pdf类型");
            return false;
        }
        $(this).siblings(".tipss").text(file.name)
    });
    /*  添加内容部分 end*/


})