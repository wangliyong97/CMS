<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/3/27
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Activity sharing</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/page/subscribe_page.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/loaders.css" rel="stylesheet">
    <style type="text/css">
        .loader-inner>div {
            background-color: #907f819e
        }
        .lanmu img {
            height: 100px;
            float: left;
            margin-right: 20px;
        }
        .lanmu h1 {
            font-size: 22px;
            margin-bottom: 15px;
        }
        .lanmu p {
            color: #666;
        }
    </style>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <div class="box">
<%--        <div class="subscribeList" id="subscribeList">--%>
<%--            <h2 class="hometitle">--%>
<%--                <span class="tagTitle"> </span>订阅活动--%>
<%--            </h2>--%>
<%--            <ul>--%>

<%--            </ul>--%>
<%--            <p class="page" style='display:none'>--%>
<%--            <p>--%>
<%--            <p class="pageMin">--%>
<%--            <p>--%>
<%--        </div>--%>
    </div>
    <a href="#" class="top cd-top animated ">Top</a>
</article>
<script src="${pageContext.request.contextPath}/js/page/subscribe.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<script type="text/javascript">
    var userId = ${sessionScope.userId};
</script>
</body>
</html>
