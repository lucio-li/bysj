$(function() {
    $("#catalogSecondPart").hide();
    $("#libraryType").change(function() {//选择不同库名，则更改一级目录名
        $("#catalogSecondPart").hide();
        var library = $("#libraryType option:selected").val();
        //更改一级目录
        $.ajax({
            url: "./getCatalogFirst",
            data :{
                library: library
            },
            success: function(res) {
                var catalogFirst = res.catalogFirst;
                $("#catalogFirst").children("option").remove();
                var optionNode = "<option value=\"all\">全部</option>";
                $("#catalogFirst").append(optionNode);
                for (var i = 0; i < catalogFirst.length; i++) {
                    var optionNode = "<option value=\"" + catalogFirst[i].code + "\">" + catalogFirst[i].name + "</option>";
                    $("#catalogFirst").append(optionNode);
                }
            }
        })

        //更改库下的内容
        $.ajax({
            url: "./getContentByLibrary",
            data :{
                library: library
            },
            success: function(res) {
                var contentList = res.contentList;
                $("#contentListPart").children().remove();
                for (var i = 0; i < contentList.length; i++) {
                   var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
                    var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
                    var spanNode = $("<span class=\"weui-cell__ft\"></span>");
                    var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

                    $("#contentListPart").append(aNode);
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
            url: "./getCatalogSecond",
            data :{
                library: library,
                parent_code: parent_code,
                level: 2
            },
            success: function(res) {
                var catalogSecond = res.catalogSecond;
                $("#catalogSecond").children("option").remove();
                var optionNode = "<option value=\"all\">全部</option>";
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
                url: "./getContentByLibrary",
                data :{
                    library: library
                },
                success: function(res) {
                    var contentList = res.contentList;
                    $("#contentListPart").children().remove();
                    for (var i = 0; i < contentList.length; i++) {
                        var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
                        var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
                        var spanNode = $("<span class=\"weui-cell__ft\"></span>");
                        var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

                        $("#contentListPart").append(aNode);
                    }

                }
            })
        } else {
            $.ajax({
                url: "./getContentFirst",
                data :{
                    library: library,
                    code: parent_code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    $("#contentListPart").children().remove();
                    for (var i = 0; i < contentList.length; i++) {
                        var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
                        var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
                        var spanNode = $("<span class=\"weui-cell__ft\"></span>");
                        var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

                        $("#contentListPart").append(aNode);
                    }

                }
            })
        }

    });
    //点击二级目录
    $("#catalogSecond").change(function() {
        var library = $("#libraryType option:selected").val();
        var parent_code = $("#catalogFirst option:selected").val();
        var code = $("#catalogSecond option:selected").val();
        if (code == "all") {//二级目录的全部
            $.ajax({
                url: "./getContentFirst",
                data :{
                    library: library,
                    code: parent_code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    $("#contentListPart").children().remove();
                    for (var i = 0; i < contentList.length; i++) {
                        var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
                        var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
                        var spanNode = $("<span class=\"weui-cell__ft\"></span>");
                        var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

                        $("#contentListPart").append(aNode);
                    }

                }
            })
        } else {
            $.ajax({
                url: "./getContentSecond",
                data :{
                    library: library,
                    code: code,
                },
                success: function(res) {
                    var contentList = res.contentList;
                    $("#contentListPart").children().remove();
                    for (var i = 0; i < contentList.length; i++) {
                        var titleNode = $("<h4 class=\"weui-media-box__title\"></h4>").text(contentList[i].title);
                        var divNode = $("<div class=\"weui-cell__bd weui-cell_primary\"></div>").append(titleNode);
                        var spanNode = $("<span class=\"weui-cell__ft\"></span>");
                        var aNode = $("<a class=\"weui-cell weui-cell_access\" href=\"../content/detail?id=" + contentList[i].id + "\"></a>").append(divNode, spanNode);

                        $("#contentListPart").append(aNode);
                    }

                }
            })
        }
    })
    $("#txtSearchTitle").keydown(function(event) {

        if (event.keyCode == 13) {//按下enter键
            var title = $(this).val();
            if (!title) {
                $("#contentListPart").show();
                $("#searchListPart").hide();

            } else {
                $("#contentListPart").hide();
                $("#searchListPart").show();
                var len = $("#contentListPart").children().length;
                var contentList = [];
                for (var i = 0; i < len; i++) {
                    var contentTitle = $("#contentListPart h4").eq(i).text();
                    if (contentTitle.indexOf(title) != -1)  {

                        var item = {};
                        item["title"] = contentTitle;
                        contentList.push(item);
                    }
                }
                loadContentList(contentList, "searchListPart");


            }

        }
    });


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
})