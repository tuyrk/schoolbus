<!--
User: 涂元坤
Mail: 766564616@qq.com
Date: 2017/11/26
Time: 20:24 星期日
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>校车预约</title>
    <link rel="stylesheet" href="statics/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/lib/fonts/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/allPage.css">
    <link rel="stylesheet" href="statics/css/imgUpload.css">
    <link rel="stylesheet" href="statics/css/order.css">
    <link rel="icon" href="statics/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon"/>
    <style>
        .img-list-2 {
            background-image: url(${pageContext.request.scheme}://schoolbus.tuyrk.cn${pageContext.request.contextPath}/${userInfo.headimg});
            background-size: cover;
            background-position: center;
        }
    </style>
</head>
<body>
<div class="container" id="main">
    <img src="statics/images/forgetPassword.png" alt="order" id="main-loginImg" class="img-responsive">
    <div class="main-form">
        <form style="margin: 0 auto;border: 1px solid transparent">
            <div class="form-img">
                <div class="img-list-2" id="upload" title="上传头像">
                    <ul class="img-list"></ul>
                </div>
                <div class="form-img-header">
                    <span>${userInfo.name}</span><br>
                    <span>${userInfo.department}</span>
                    <span>${userInfo.number}</span>
                </div>
                <div class="pull-right">
                    <!-- a 标签触发模态框 -->
                    <a data-toggle="modal" href="#myModal">
                        <i class="fa fa-bars fa-2x  fa-setting" title="保存信息" aria-hidden="true"></i>
                    </a>
                </div>
                <!-- <input type="file" id="choose" accept="image/*" multiple>-->
                <!-- <a class="btn btn-default" id="upload">上传头像</a>-->
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">设置</h4>
                            </div>
                            <div class="modal-body" align="left">
                                <a href="userinfo" class="list-group-item">
                                    <span>个人信息</span>
                                    <i class="fa fa-angle-left fa-rotate-180 pull-right" aria-hidden="true"></i>
                                </a>
                                <a href="modifyPassword" class="list-group-item">
                                    <span>修改密码</span>
                                    <i class="fa fa-angle-left fa-rotate-180 pull-right" aria-hidden="true"></i>
                                </a>
                                <a href="seeOrder" class="list-group-item">
                                    <span>查看订单</span>
                                    <i class="fa fa-angle-left fa-rotate-180 pull-right" aria-hidden="true"></i>
                                </a>
                                <a class="list-group-item" id="logout">
                                    <span>退出登录</span>
                                    <i class="fa fa-angle-left fa-rotate-180 pull-right" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal"
                                        style="padding: 6px 12px;">关闭
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
            </div>
            <div class="form-submit">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active">
                        <a href="#shiErQ" data-toggle="tab">十二桥校区</a>
                    </li>
                    <li>
                        <a href="#wangJiaG" data-toggle="tab">汪家拐校区</a>
                    </li>
                    <li>
                        <a href="#renNan" data-toggle="tab">人南校区</a>
                    </li>
                </ul>

                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="shiErQ">
                        <c:forEach items="${buses}" var="bus" begin="0" end="${fn:length(buses)}">
                            <c:if test="${bus.start == '十二桥校区' || bus.end == '十二桥校区'}">
                                <div class="shiErQ-wenJ">
                                    <span class="xiaoQu-content xiaoQu-head">${bus.start}
                                        <i class="fa fa-long-arrow-right" aria-hidden="true"></i>${bus.end}
                                    </span>
                                    <span class="xiaoQu-content">已预约人数：
                                        <span class="xiaoQu-detail">${bus.reservation}</span>
                                    </span>
                                    <span class="xiaoQu-content">剩余座位数：<span class="xiaoQu-detail">${bus.surplus}</span></span>
                                    <span class="xiaoQu-content">预约状态：<span
                                            class="xiaoQu-detail">${bus.state}</span></span>
                                    <span class="xiaoQu-content">发车时间：<span
                                            class="xiaoQu-detail">${bus.time}</span></span>
                                    <c:choose>
                                        <c:when test="${bus.state == '可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" value="现在预约">
                                        </c:when>
                                        <c:when test="${bus.state == '不可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" disabled value="不可预约">
                                        </c:when>
                                    </c:choose>
                                </div>
                                <hr/>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="tab-pane fade" id="wangJiaG">
                        <c:forEach items="${buses}" var="bus" begin="0" end="${fn:length(buses)}">
                            <c:if test="${bus.start == '汪家拐校区' || bus.end == '汪家拐校区'}">
                                <div class="shiErQ-wenJ">
                            <span class="xiaoQu-content xiaoQu-head">${bus.start}
                                <i class="fa fa-long-arrow-right" aria-hidden="true"></i>${bus.end}</span>
                                    <span class="xiaoQu-content">已预约人数：<span
                                            class="xiaoQu-detail">${bus.reservation}</span></span>
                                    <span class="xiaoQu-content">剩余座位数：<span class="xiaoQu-detail">${bus.surplus}</span></span>
                                    <span class="xiaoQu-content">预约状态：<span
                                            class="xiaoQu-detail">${bus.state}</span></span>
                                    <span class="xiaoQu-content">发车时间：<span
                                            class="xiaoQu-detail">${bus.time}</span></span>
                                    <c:choose>
                                        <c:when test="${bus.state == '可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" value="现在预约">
                                        </c:when>
                                        <c:when test="${bus.state == '不可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" disabled value="不可预约">
                                        </c:when>
                                    </c:choose>
                                </div>
                                <hr/>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="tab-pane fade" id="renNan">
                        <c:forEach items="${buses}" var="bus" begin="0" end="${fn:length(buses)}">
                            <c:if test="${bus.start == '人南校区' || bus.end == '人南校区'}">
                                <div class="shiErQ-wenJ">
                                    <span class="xiaoQu-content xiaoQu-head">${bus.start}<i
                                            class="fa fa-long-arrow-right" aria-hidden="true"></i>${bus.end}</span>
                                    <span class="xiaoQu-content">已预约人数：<span
                                            class="xiaoQu-detail">${bus.reservation}</span></span>
                                    <span class="xiaoQu-content">剩余座位数：<span class="xiaoQu-detail">${bus.surplus}</span></span>
                                    <span class="xiaoQu-content">预约状态：<span
                                            class="xiaoQu-detail">${bus.state}</span></span>
                                    <span class="xiaoQu-content">发车时间：<span
                                            class="xiaoQu-detail">${bus.time}</span></span>
                                    <c:choose>
                                        <c:when test="${bus.state == '可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" value="现在预约">
                                        </c:when>
                                        <c:when test="${bus.state == '不可预约'}">
                                            <input type="button" class="btn btn-primary order-button" name="${bus.id}"
                                                   id="${bus.id}" disabled value="不可预约">
                                        </c:when>
                                    </c:choose>
                                </div>
                                <hr/>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="statics/js/lib/jquery-3.2.1.js"></script>
<script src="statics/js/lib/bootstrap.min.js"></script>
<script src="statics/js/order.js"></script>
</html>