/**
 * Created by lq on 2018/2/19.
 */
$(function() {

    //点击上传文件
    $("#image1").click(function () {
        if(typeof FileReader == 'undefined') {
            alert("请使用支持HTML5的主流浏览器");
            return;
        }
        $("#fileBtn").click();
    })
    $("#fileBtn").change(function () {
        var file = document.getElementById("fileBtn").files[0];
        if (!/image\/\w+/.test(file.type)) {
            alert("请确保文件为图像类型");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (e) {
            $(".addImg").attr("src", this.result);
            $(".addImg").show();
        }
    });
    $("#addImageBtn").click(function(){
        var file = document.getElementById("fileBtn").files[0];
        if (!file) {
            alert("请先选择文件");
            return;
        }
        document.getElementById('addImageForm').submit();//上传文件
    })

    $(".deleteBtn").click(function(item) {
        var name = $(this).attr("data-name");
        var id = $(this).attr("data-id");
        //删除图片素材
        if (confirm("确认是否删除名称为" + name + "的图片")) {
            $.ajax({
                url: "../image/delete",
                data :{
                    id:id
                },
                success: function(res) {
                    if (res.result == "success") {
                        alert("删除成功")
                    } else {
                        alert("删除失败，请稍后重试")
                    }
                    location.href = "./imagesMessage"
                }
            })
        } else {

        }

    });

    //点击添加图片按钮，显示添加图片的部分
    $(".showAddPart").click(function(item){

        $("#addPart").show();
        location.href = "#add";

    })


})
