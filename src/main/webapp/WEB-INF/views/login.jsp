<!--
    User: 涂元坤
    Mail: 766564616@qq.com
    Date: 2017/11/26
    Time: 20:24 星期日
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,height=device-height,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>登录</title>
    <link rel="stylesheet" href="statics/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/lib/fonts/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/allPage.css">
    <link rel="stylesheet" href="statics/css/imgUpload.css">
    <link rel="icon" href="statics/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon" />
</head>
<body>
<div class="container" id="main">
    <!--背景图片-->
    <img src="statics/images/login.png" alt="login" id="main-loginImg" class="img-responsive">
    <!--表单详细内容-->
    <div class="main-form">
        <!--晃动提示-->
        <div class="flex-container">
            <div class="flex-item" id="shake">
                <span>工号或密码有误！</span>
            </div>
        </div>
        <form style=" margin: 0 auto;">
            <div class="form-img">
                <div class="img-list-2" id="upload" title="上传头像">
                    <ul class="img-list">
                    </ul>
                </div>
            </div>
            <div class="form-submit">
                <div class="form-group has-feedback" style="margin-bottom: 5%;">
                    <label for="username" class="sr-only">账户</label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa-fw fa-i"></i> </span>
                        <input id="username" name="username" type="text" class="form-control input"
                               placeholder="工号">
                    </div>
                </div>
                <div class="form-group has-feedback" style="margin-bottom: 5%;">
                    <label for="password" class="sr-only">密码</label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-fw fa-i"></i> </span>
                        <input id="password" name="password" type="password" class="form-control input"
                               placeholder="密码">
                    </div>
                </div>
                <div class="form-group" align="center">
                    <button class="btn  btn-outline-inverse btn-block btn-primary" type="button" name="login" id="login"
                            style="margin-bottom: 1%;">登录
                    </button>
                    <label for="forget"><a id="forget">忘记密码?</a></label>
                </div>
            </div>
            <div class="form-footer">
                <div id="footer" align="center" style="margin-top: 50%;">
                    <span style="font-size: 0.6em;color: #666666;">成都中医药大学校园交通车预约</span>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="statics/js/lib/jquery-2.1.1.min.js"></script>
<script src="statics/js/login.js"></script>
</html>