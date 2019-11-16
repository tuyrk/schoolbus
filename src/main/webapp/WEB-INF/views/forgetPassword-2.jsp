<!--
    User: 涂元坤
    Mail: 766564616@qq.com
    Date: 2017/11/26
    Time: 20:24 星期日
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,height=device-height,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>找回密码</title>
    <link rel="stylesheet" href="statics/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/lib/fonts/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/allPage.css">
    <link rel="stylesheet" href="statics/css/forgetPassword.css">
    <link rel="icon" href="statics/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon" />
</head>
<body>
<div class="container" id="main">
    <img src="statics/images/forgetPassword.png" alt="forgetPassword" id="main-loginImg" class="img-responsive">
    <div class="main-form">
        <!--晃动提示-->
        <div class="flex-container">
            <div class="flex-item" id="shake">
                <span>密码应在6-16位数字、字母，下划线之间！</span>
            </div>
        </div>
        <div class="form-header">
            <div class="pull-left">
                <span>
                    <a href="retrieve" class="fa-back-a">
                        <i class="fa fa-angle-double-left fa-lg " title="返回" aria-hidden="true"></i>
                        <span class="fa-back-text">上一步</span>
                    </a>
                </span>
            </div>

            <!--返回图标-->
        </div>
        <hr>
        <form style=" margin: 0 auto;">
            <div class="form-submit">
                <div class="form-group">
                    <div class="col-sm-12">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <input type="password" class="form-control" id="rePassword"  placeholder="请确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <button class="btn  btn-outline-inverse btn-block btn-primary" type="button" id="savePassword"
                                style="margin-top: 7%;">保存
                        </button>
                    </div>
                </div>
            </div>
            <div class="form-footer">
                <div id="footer" align="center">
                    <span style="font-size: 0.6em;color: #666666;">成都中医药大学校园交通车预约</span>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="statics/js/lib/jquery-3.2.1.js"></script>
<script src="statics/js/forgetPassword.js"></script>
</html>