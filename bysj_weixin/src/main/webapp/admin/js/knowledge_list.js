$(function() {
    $("#catalogSecondPart").hide();
    $("#libraryType").change(function() {//选择不同库名，则更改一级目录名
        $("#catalogSecondPart").hide();
        var library = $("#libraryType option:selected").val();
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

    })
    $("#catalogFirst").change(function() {//选择不同一级目录，则更改二级目录名
        $("#catalogSecondPart").show();
        var library = $("#libraryType option:selected").val();
        var parent_code = $("#catalogFirst option:selected").val();
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

    })
})