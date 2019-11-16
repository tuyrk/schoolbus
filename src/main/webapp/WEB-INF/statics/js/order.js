$(function () {
    $("#logout").on('click', function (e) {
        e.preventDefault();
        if (confirm('是否确认退出登录？')) {
            location.href = "logout";
        }
    });

    $("#myTabContent").on("click", 'input', function () {
        $.ajax({
            url: "bespeak",
            data: {
                "id": this.id
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    alert("预约成功");
                    location.reload();
                }
            },
            error: function (error) {
                console.log(error.responseText)
            }
        })
    });

    $("#orderHistory").on("click", 'input', function () {
        $.ajax({
            url: "deleteOrder",
            data: {
                "id": this.id
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    if (data == "success") {
                        alert("删除成功");
                        location.reload();
                    }
                }
            }
        })
    });

    $("#currentOrder").on("click", 'input', function () {
        $.ajax({
            url: "cancelOrder",
            data: {
                "id": this.id,
                "busId": $("#bus-id").val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    alert("取消成功");
                    location.reload();
                }
            }
        })
    })
});