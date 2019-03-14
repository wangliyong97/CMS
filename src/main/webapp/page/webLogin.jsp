<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/2/23
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Activity sharing - 登录</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/common.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/core.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body style="padding: 3% 4%">
<div class="row">
    <div class="col-md-5" style="padding-left: 64px">
        <div class=" text-center">
            <br>
            <br>
            <br>
            <br>
            <br>
            <small style="color: gray;font-size: 1.3em" class="lead">请输入您的用户名和密码</small>
            <br>
            <br>
            <hr class="default-line">
            <br>

            <input type="text" id="loginUserName" placeholder="用户名" class="jianshu-style-input login-input">
            <br>
            <br>

            <input type="password" class="jianshu-style-input login-input" id="loginPassword"
                   placeholder="密码">
            <br>
            <br>

            <button class="button-success" style="width: 80%;height: 45px" id="loginBtn" onclick="login()">登录
            </button>
            <br>
            <br>
            <span class="error-msg" id="loginErrorMsg"></span>
            <br>
            <p style="opacity: 0.5;" class="text-center">还没账号，去<a href="/register">&nbsp;注册</a></p>
        </div>
    </div>
    <div class="col-md-7 text-right" style="padding-right: 100px;padding-left: 50px">
        <img src="/images/logo.png" class="logo website-logo">

        <br>
        <hr class="default-line">
        <br>

        <p style="padding-top: 16px;line-height:25px;padding-left:32px;padding-right: 16px;color: dimgray"
           class="text-right">
            在线分享活动，注册用户在线申请<br><br>
            显示当前注册用户所参与的活动，及个人信息<br><br>
            注册成为用户，就能参与活动或举办活动<br>
        </p>
        <br>
        <br>
        <br>
        <br>
    </div>
</div>

<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/comm.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/login.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/plugins/adaptiveBgColor/jquery.adaptive-backgrounds.js'></script>

<script>
    var show = false;
</script>
</body>
</html>