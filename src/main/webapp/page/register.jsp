<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/2/23
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Activity sharing - 注册</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap3.3.7.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/common.css">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap3.3.7.min.js"></script>
</head>
<body>

<%--登录对话框--%>

<div class="container-">
    <div class="row">
        <div class="col-md-5 register-left con">
            <p class="page-title">
                Activity sharing
            </p>
        </div>
        <div class="col-md-1 con"></div>
        <div class="col-md-4 con">

            <div style="height: 20%">
                <br>
                <br>
                <br>
                <p>
                <h1>欢迎注册</h1>
                </p>
                <br>
                <table>
                    <tr>
                        <td class="step-title-choose"><span class="step-choose">1</span>&nbsp;注册账户&nbsp;<span>————&nbsp;&nbsp;</span>
                        </td>
                        <td class="step-title" id="stepTitle2"><span id="stepTitle2_" class="step">2</span>&nbsp;补全信息&nbsp;<span>————&nbsp;&nbsp;</span>
                        </td>
                        <td class="step-title" id="stepTitle3"><span id="stepTitle3_" class="step">3</span>&nbsp;完成
                        </td>
                    </tr>
                </table>
            </div>

            <div style="height: 50%">
                <br>
                <br>
                <br>
                <br>
                <div id="inputAccount">

                    <form>
                        <input type="text" id="registerUserName" placeholder="用户名" class="form-input"><br><br><br>
                        <input type="password" class="form-input" id="registerPassword" placeholder="密码" autocomplete="off"><br><br><br>
                        <input type="password" class="form-input" id="conformPassword" placeholder="确认密码" autocomplete="off">
                    </form>

                </div>

                <div id="inputProfile" style="display: none;">

                    <form>
                        <input type="text" id="registerNickName" placeholder="昵称" class="form-input"><br><br><br>
                        <input type="email" class="form-input" id="registerEmail" placeholder="email"><br><br><br>
                        <input type="text" class="form-input" id="registerIntro" placeholder="个人介绍"><br><br><br>
                        <%--<textarea class="default-textarea" id="registerAboutMe" placeholder="博主自述"></textarea>--%>
                    </form>

                </div>

                <div id="inputFinish" style="display: none;">
                    <br>
                    <h3 id="finalInfo">
                    </h3>

                </div>
            </div>

            <div style="height: 20%">
                <span class="error-msg" id="registerErrorMsg"></span>
                <br>
                <br>
                <button class="button-success" style="padding: 8px 64px;" onclick="nextStep()" id="nextStep">下一步
                </button>
            </div>

        </div>
        <div class="col-md-1 con"></div>
        <div class="col-md-1 con">
            <p class="text-right">
                <br>
                <br>
                <a href="/login">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </p>
        </div>
    </div>
</div>

<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/comm.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/register.js"></script>

</body>
</html>

