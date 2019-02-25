<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>活动管理系统</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <meta name="description" content="活动管理系统，一个基于ssm框架的活动分享" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/loaders.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <style type="text/css">
        .loader-inner>div {
            background-color: #907f819e
        }
    </style>
</head>
<body>
<%@ include file="top.jsp"%>
<article style="opacity:0">
    <div class="lbox ">
        <div class="recommend-top">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item tupian1 active">
                        <img class="d-block w-100 " src="images/t03.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item tupian2">
                        <img class="d-block w-100 " src="images/t03.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item tupian3">
                        <img class="d-block w-100 " src="images/t03.jpg" alt="Third slide">
                    </div>
                    <div class="carousel-item tupian4">
                        <img class="d-block w-100 " src="images/t03.jpg" alt="Four slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <div class="clearblank"></div>

        <div class="zhuanti whitebg">
            <h2 class="htitle">特别推荐</h2>
            <ul class="animated ">

            </ul>
        </div>

        <div class="ad whitebg">
            <a href="#" target="_blank"> <img
                    src="${pageContext.request.contextPath}/images/longad2.png">
            </a>
        </div>

        <div class="newblogs bloglist whitebg animated fadeIn"
             style="display:none;animation-delay:0.3s">
            <h2 class="htitle">最新活动</h2>
            <ul class="animated ">

            </ul>
            <p class="page" style='display:none'>
            <p>
            <p class="pageMin">
            <p>
        </div>

    </div>

    <div class="rbox">
        <div class="applybtn">
            <a href="${pageContext.request.contextPath}/page/apply.jsp">申请活动 - 入口</a>
        </div>
        <div class="dj paihang animated fadeIn" style="animation-delay:0.3s">
            <h2 class="htitle">点击排行</h2>
            <ul class="">
            </ul>
        </div>
        <div class="whitebg notice">
            <h2 class="htitle">热门活动</h2>
            <ul>
            </ul>
        </div>
    </div>

    <a href="#" class="top cd-top animated ">Top</a>
</article>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/page/index.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
</body>

</html>
