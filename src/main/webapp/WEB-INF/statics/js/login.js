$(function () {
    var $shake = $('#shake');
    $shake.addClass('shake');

    $('#username').bind('input propertychange', function () {
        var $username = $("#username").val();

        if (($username.length == 5 && $username.indexOf("ls") >= 0) || $username.length == 8) {
            var url = window.location.protocol + "//" + window.location.host + "/schoolbus/statics/head/" + $username + ".jpeg";
            $(".img-list-2").css("background-image", "url(" + url + ")");
        }
    });

    $('#login').click(function () {
        var $username = $("#username");
        var $password = $("#password");
        var pattern_user = /^([0-9a-z]{5})|(\d{8})$/;
        var pattern_Pass = /^[0-9a-zA-Z_]{6,16}$/;
        if ($username.val() == '') {
            setDivCenter("工号不能为空");
            $username.focus();
            return;
        }
        if (!pattern_user.test($username.val())) {
            setDivCenter("工号输入有误");
            $username.focus();
            return;
        }
        if ($password.val() == '') {
            setDivCenter("密码不能为空");
            $password.focus();
            return;
        }
        if (!pattern_Pass.test($password.val())) {
            setDivCenter("密码输入有误");
            $password.focus();
            return;
        }
        $.ajax({
            url: "checkLogin",
            data: {
                "username": $username.val(),
                "password": $password.val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    location.href = "order";
                } else {
                    setDivCenter("工号或密码错误");
                    $username.focus();
                }
            }
        })
    });

    $("#forget").on('click', function () {
        var username = $("#username").val();
        if (username == null || username == "") {
            setDivCenter("请先输入工号");
        } else {
            location.href = "retrieve?username=" + username;
        }
    });

    function setDivCenter(str) {
        $("#shake").text(str);
        $shake.show().delay(2500).fadeOut();
    }
});