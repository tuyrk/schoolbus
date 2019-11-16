$(function () {
    var $shake = $('#shake');
    $shake.addClass('shake');
    var pattern_Pass = /^[0-9a-zA-Z_]{6,16}$/;

    $("#modifySuccess").on('click', function () {
        if (!pattern_Pass.test($("#oldPassword").val())) {
            setDivCenter('您输入的旧密码有误！');
            $("#oldPassword").focus();
            return;
        }
        if (!pattern_Pass.test($("#newPassword").val())) {
            setDivCenter('密码应在6-16位数字、字母，下划线之间！');
            $("#newPassword").focus();
            return;
        }
        if (($('#reNewPassword').val() != $('#newPassword').val())) {
            setDivCenter('两次密码不一致！');
            return;
        }
        $.ajax({
            url: "modifyPasswordService",
            data: {
                "oldPassword": $("#oldPassword").val(),
                "newPassword": $("#newPassword").val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    alert("修改成功,请重新登录");
                    location.href = "/schoolbus";
                } else if (data == "error") {
                    setDivCenter("旧密码错误");
                } else {
                    setDivCenter("修改失败");
                }
            }
        });
    });

    function setDivCenter(str) {
        $("#shake").text(str);
        $shake.show().delay(2500).fadeOut();
    }
});