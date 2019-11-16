<!--
User: 涂元坤
Mail: 766564616@qq.com
Date: 2017/11/26
Time: 20:24 星期日
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,height=device-height,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>查看订单</title>
    <link rel="stylesheet" href="statics/css/lib/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/lib/fonts/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/allPage.css">
    <link rel="stylesheet" href="statics/css/checkOrder.css">
    <link rel="icon" href="statics/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="statics/images/favicon.ico" type="image/x-icon"/>
</head>
<body ng-app="app" ng-controller="UploaderController">
<div class="container" id="main">
    <img src="statics/images/forgetPassword.png" alt="forgetPassword" id="main-loginImg" class="img-responsive">
    <div class="main-form">
        <div class="form-header">
            <div class="pull-left">
                <span>
                    <a href="order" class="fa-back-a">
                        <i class="fa fa-angle-double-left fa-lg " title="返回" aria-hidden="true"></i>
                        <span class="fa-back-text">查看订单</span>
                    </a>
                </span>
            </div>
            <!--返回图标-->
        </div>
        <hr>
        <form style=" margin: 0 auto;">
            <div class="form-submit">

                <div class="currentOrder" id="currentOrder">
                    <div>
                        <p>当前订单</p>
                    </div>
                    <c:if test="${nowOrder.id != 0}">
                        <i class="fa fa-bus" aria-hidden="true"></i>
                        <span>发车方向：
                            <span>
                                <span id="start">${nowOrder.start}</span>
                                <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                                <span id="end">${nowOrder.end}</span>
                            </span>
                        </span><br>
                        <i class="fa fa-bus invisible" aria-hidden="true"></i>
                        <span>发车时间：
                            <span id="time">${nowOrder.time}</span>
                        </span><br>
                        <i class="fa fa-bus invisible" aria-hidden="true"></i>
                        <span>订单状态：<span>预约成功</span></span><br>
                        <i class="fa fa-bus invisible" aria-hidden="true"></i>
                        <input type="button" class="btn btn-primary order-button" name="${nowOrder.id}"
                               id="${nowOrder.id}" value="取消订单">
                        <input id="bus-id" type="hidden" value="${nowOrder.busId}">
                    </c:if>
                </div>

                <div class="orderHistory" id="orderHistory">
                    <div>
                        <p>历史订单</p>
                    </div>
                    <c:forEach items="${historyOrder}" var="order">
                        <c:if test="${order.id != 0}">
                            <div style="clear: both">
                                <i class="fa fa-bus" aria-hidden="true"></i>
                                <span>发车方向：
                                        <span>
                                            <span>${order.start}</span>
                                            <i class="fa fa-long-arrow-right" aria-hidden="true"></i>
                                            <span>${order.end}</span>
                                        </span>
                                    </span><br>
                                <i class="fa fa-bus invisible" aria-hidden="true"></i>
                                <span>发车时间：
                                    <span>${order.time}</span>
                                </span><br>
                                <i class="fa fa-bus invisible" aria-hidden="true"></i>
                                <span>订单状态：
                                    <span>${order.state}</span>
                                </span><br>
                                <i class="fa fa-bus invisible" aria-hidden="true"></i>
                                <input type="button" name="${order.id}" id="${order.id}" value="删除订单"
                                       class="btn btn-primary order-button">
                            </div>
                            <hr>
                        </c:if>
                    </c:forEach>
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
<script src="statics/js/order.js"></script>
</html>