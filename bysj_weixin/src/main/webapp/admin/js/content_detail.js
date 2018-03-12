$(function() {

    $("#download").click(function() {
        //下载附件
        var id = $(this).attr("data-id");
        location.href = "../attachment/preview?id=" + id;
    })
})