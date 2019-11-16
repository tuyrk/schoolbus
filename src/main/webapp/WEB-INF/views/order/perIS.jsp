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
    <title>个人信息</title>
    <link rel="stylesheet" href="statics/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/lib/fonts/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/allPage.css">
    <link rel="stylesheet" href="statics/css/imgUpload.css">
    <link rel="stylesheet" href="statics/css/perIM.css">
    <link rel="icon" href="statics/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon" />
    <style>
        .img-list-2 {
            background-image: url(${pageContext.request.scheme}://schoolbus.tuyrk.cn${pageContext.request.contextPath}/${userInfo.headimg});
            background-size: cover;
            background-position:center;
        }
    </style>
</head>
<body>
<div class="container" id="main">
    <img src="statics/images/perIM.png" alt="preIM" id="main-loginImg" class="img-responsive">
    <!--背景图片-->
    <div class="main-form">
        <div class="form-header">
            <div class="pull-left">
                <a href="order">
                    <i class="fa fa-angle-double-left fa-lg  fa-back" title="返回" aria-hidden="true"></i>
                </a>
            </div>
            <!--返回图标-->
            <div class="pull-right">
                <a href="modifyUserinfo">
                    <i class="fa fa-pencil-square-o fa-lg  fa-new-save" title="修改信息" aria-hidden="true"></i>
                </a>
            </div>
            <!--修改信息图标-->
        </div>
        <!--页头-->
        <form style=" margin: 0 auto;">
            <div class="form-img">
                <div class="img-list-2" id="upload" title="上传头像">
                    <ul class="img-list">
                    </ul>
                </div>
            </div>
            <!--上传头像-->
            <div class="form-submit">
                <div class="inf-group pull-left">
                    <div class="information pull-left">
                        <div class="pull-left textFa-icon">
                            <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-user fa-stack-1x"></i>
                           </span>
                        </div>
                        <div class="pull-left textFa-text">
                            <span class="pull-left textSpan">&nbsp; 姓名</span><br>
                            <textarea name="username" id="username" class="textArea" disabled="true"
                                      placeholder="张珊">${userInfo.name}</textarea>
                        </div>
                    </div>
                    <!--姓名-->
                    <div class="information pull-right">
                        <div class="pull-right textFa-text">
                            <span class="pull-left textSpan">&nbsp; 性别</span><br>
                            <textarea name="sex" id="sex" class="textArea" disabled="true"
                                      placeholder="女">${userInfo.sex}</textarea>
                        </div>
                        <div class="pull-right textFa-icon">
                            <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-venus-mars fa-stack-1x"></i>
                           </span>
                        </div>
                    </div>
                    <!--性别-->
                    <div class="information pull-left">
                        <div class="pull-left textFa-icon">
                            <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-building fa-stack-1x"></i>
                           </span>
                        </div>
                        <div class="pull-left textFa-text">
                            <span class="pull-left textSpan">&nbsp; 部门</span><br>
                            <textarea name="department" id=department" class="textArea" disabled="true"
                                      placeholder="医学信息工程学院">${userInfo.department}</textarea>
                        </div>
                    </div>
                    <!--部门-->
                    <div class="information pull-right">
                        <div class="pull-right textFa-text">
                            <span class="pull-left textSpan">&nbsp; 工号</span><br>
                            <textarea name="jobnumber" id="jobnumber" class="textArea" disabled="true"
                                      placeholder="201944901011">${userInfo.number}</textarea>
                        </div>
                        <div class="pull-right textFa-icon">
                          <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-list-ol fa-rotate-90 fa-stack-1x"></i>
                           </span>
                        </div>
                    </div>
                    <!--工号-->
                    <div class="information pull-left">
                        <div class="pull-left textFa-icon">
                           <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-phone fa-stack-1x"></i>
                           </span>
                        </div>
                        <div class="pull-left textFa-text">
                            <span class="pull-left textSpan">&nbsp; 电话</span><br>
                            <textarea name="phone" id="phone" class="textArea"
                                      disabled="true">${userInfo.phone}</textarea>
                        </div>
                    </div>
                    <!--电话-->
                    <div class="information pull-right">
                        <div class="pull-right textFa-text">
                            <span class="pull-left textSpan">&nbsp; 电子邮箱</span><br>
                            <textarea name="email" id="email" class="textArea"
                                      disabled="true">${userInfo.email}</textarea>
                        </div>
                        <div class="pull-right textFa-icon">
                           <span class="fa-stack fa-icon">
                                 <i class="fa fa-square-o fa-stack-2x"></i>
                                 <i class="fa fa-envelope-o fa-stack-1x"></i>
                           </span>
                        </div>
                    </div>
                    <!--电子邮箱-->
                </div>
            </div>
            <!--表单提交-->
            <div class="form-footer">
                <div id="footer" align="center">
                    <span style="font-size: 0.6em;color: #666666;">成都中医药大学校园交通车预约</span>
                </div>
            </div>
            <!--页脚-->
        </form>
    </div>
    <!--页面主内容-->
</div>
</body>
<script src="statics/js/lib/jquery-3.2.1.js"></script>
</html>