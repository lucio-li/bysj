wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    //获取当前时间
    var nowTime = new Date();
    var m = nowTime.getMonth() + 1;
    var d = nowTime.getDate();
    var h = nowTime.getHours();
    var minutes = nowTime.getMinutes();
    var s = nowTime.getSeconds();
    if (m < 10) {
        m = "0" + m;
    }
    if (d < 10) {
        d ="0" + d;
    }
    if (h < 10) {
        h = "0" + h;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (s < 10) {
        s = "0" + s;
    }
    var t = nowTime.getFullYear() + "-" + m + "-"
        + d + " " + h + ":"
        + minutes + ":" + s;
    $("#time").val(t);




    var localIds = [];
    $("#uploaderInput").click(function() {
        wx.chooseImage({
            count: 9, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

                for(var i = 0; i < localIds.length; i++) {
                    var divNode = $("<div class=\"chooseImages\"></div>");
                    var imageTmp = $("<image class=\"chooseImagesList\"  data-index=\"" + i + "\" src=\"" + localIds[i] + "\"/>");
                    var imageDel = $("<image class=\"deleteImageBtn\"  data-index=\"" + i + "\" src=\"../../admin/images/delete.png\"/>");
                    divNode.append(imageTmp,imageDel);
                    $(".header").append(divNode);
                }

            }
        });
    })

    $("#submitBtn").click(function() {
        var content = $("#content").val();
        if(!content) {
            alert("请先输入内容");
            $("#content").focus();
            return;
        }
        $.ajax({
            url: './content', //仅为示例，并非真实的接口地址
            //url: 'http://127.0.0.1:8089/lili/upload/content',
            data: {
                time: $("#time").val(),
                content: content,
                avatarUrl:$("#avatarUrl").val(),
                nickname: $("#nickname").val()

            },
            success: function (res) {
                var directory = res["directory"];
                var i = 0, length = localIds.length;
                upload(directory, i, length);
            },
            fail: function (e) {
                console.log("上传content失败")
                console.log(e)
            }
        })
    })
    function upload(directory, i, length) {
        wx.uploadImage({
            localId: localIds[i], // 需要上传的图片的本地ID，由chooseImage接口获得
            // isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
                //获取到serverId后，后台取下载保存图片
                $.ajax({
                    url: './image', //仅为示例，并非真实的接口地址
                    //url: 'http://127.0.0.1:8089/lili/upload/content',
                    data: {
                        media_id: serverId,
                        directory: directory
                    },
                    success: function (res) {
                        console.log(res.data)
                        i++;
                        if (i < length) {
                            upload(directory, i, length);
                        } else {
                            location.href = "../moments/list";
                        }

                    }
                })
            }
        });
    }
});