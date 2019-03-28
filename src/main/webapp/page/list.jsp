<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/2/14
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Activity sharing</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/loaders.css" rel="stylesheet">
    <style type="text/css">
        .loader-inner>div {
            background-color: #907f819e
        }
        .whitebg {
            background: #fff;
            border-radius: 3px;
            padding: 20px;
            margin-bottom: 20px;
            overflow: hidden;
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
    <div class="lbox">
        <div class="newblogs bloglist">
            <h2 class="hometitle">
                <span class="tagTitle"> </span>活动列表
            </h2>
            <ul>

            </ul>
            <p class="page" style='display:none'>
            <p>
            <p class="pageMin">
            <p>
        </div>
    </div>

    <div class="rbox">
        <input name="keyword" id="keyword" value="${keyword}"
               type="hidden">
        <div class="cloud animated fadeIn whitebg">
            <h2 class="cloud_hometitle">活动类别</h2>
            <ul>

            </ul>
        </div>
        <div class="tuijian2  animated fadeIn whitebg">
            <h2 class="cloud_hometitle">推荐活动</h2>
            <ul class="tjpic animated fadeIn">
                <i><img src="images/t03.jpg"></i>
            </ul>
            <ul class="sidenews">

            </ul>
        </div>

        <div class="djpaihang dj whitebg dianji animated fadeIn"
             style="display:none;animation-delay:0.3s">
            <h2 class="cloud_hometitle">点击排行</h2>
            <ul class="click">

            </ul>
        </div>

    </div>
    <a href="#" class="top cd-top animated ">Top</a>
</article>
<script src="${pageContext.request.contextPath}/js/page/list.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
</body>
</html>