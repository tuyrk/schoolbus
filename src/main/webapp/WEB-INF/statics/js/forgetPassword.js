$(function () {
    var $shake = $('#shake');
    $shake.addClass('shake');
    var pattern_email = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/;
    var pattern_securityCode = /^\d{6}/;
    var pattern_Pass = /^[0-9a-zA-Z_]{6,16}$/;

    $("#getCode").on('click', function () {
        var $getCode = $("#getCode");
        var timer = 60;
        var settime = setInterval(function () {
            $getCode.attr('disabled', 'true');
            $getCode.css({
                color: 'red'
            });
            $getCode.text(--timer + 's后重新发送');
            if (timer == -1) {
                clearInterval(settime);
                $getCode.removeAttr('disabled');
                $getCode.css({
                    color: 'darkgrey'
                });
                $getCode.text("获取验证码");
            }
        }, 1000);
        $.ajax({
            url: "sendCode",
            data: {
                "email": $("#email").val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    alert("发送成功");
                } else {
                    alert("发送失败");
                }
            }
        });
    });
    $("#email").on('blur', function () {
        if (pattern_email.test($("#email").val())) {
            $('#getCode').removeClass('disabled');
            $('#securityCode').removeAttr('disabled');
        }
        else {
            setDivCenter();
        }
    });
    $("#next").on('click', function (e) {
        if (!pattern_securityCode.test($("#securityCode").val())) {
            e.preventDefault();
            setDivCenter();
            return;
        }
        console.log($("#securityCode").val());
        $.ajax({
            url: "retrieveService",
            data: {
                "securityCode": $("#securityCode").val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    location.href = "confirm";
                } else {
                    alert("验证码错误");
                }
            }
        });
    });

    $("#savePassword").on('click', function () {
        if (!pattern_Pass.test($("#password").val())) {
            setDivCenter('密码应在6-16位数字、字母，下划线之间！');
            $("#password").focus();
            return;
        }
        if ($('#rePassword').val() != $('#password').val()) {
            setDivCenter('两次密码不一致！');
            return;
        }
        $.ajax({
            url: "forgetPassword",
            data: {
                "rePassword": $('#rePassword').val()
            },
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == "success") {
                    alert("保存成功，即将返回登录页面");
                    location.href = '/';
                } else {
                    setDivCenter("保存失败,请重新尝试");
                }
            }
        });
    });

    function setDivCenter(str) {
        $("#shake").text(str);
        $shake.show().delay(2500).fadeOut();
    }

});