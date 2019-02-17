<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>活动管理系统</title>
<link rel="shortcut icon"
  href="${pageContext.request.contextPath}/images/favicon.ico">
<meta name="keywords" content="活动管理系统" />
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
<%--style="background:url(${pageContext.request.contextPath}/images/bj.png) repeat top left scroll;z-index:-2"--%>
<body>
  <%@ include file="top.jsp"%>
  <article style="opacity:0">
    <div class="lbox ">
      <div class="banbox">
        <div class="banner">
          <div id="banner" class="fader">
            <%--<li class="slide" style=" "><a href="https://www.4562.com/" target="_blank">--%>
              <%--<img src="${pageContext.request.contextPath}/images/5e9a97fc44fc12475b6b0e87e2ff5806.jpg"></a></li>--%>
                <li class="slide tupian1" style=" ">
                    <img src="images/t03.jpg">
                </li>
                <li class="slide tupian2" style=" ">
                    <img src="images/t03.jpg">
                </li>
                <li class="slide tupian3" style=" ">
                    <img src="images/t03.jpg">
                </li>
                <li class="slide tupian4" style=" ">
                    <img src="images/t03.jpg">
                </li>
            <div class="fader_controls">
              <div class="pic_page prev" data-target="prev"></div>
              <div class="pic_page next" data-target="next"></div>
              <ul class="pager_list">
              </ul>
            </div>
          </div>
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
            <a href="${pageContext.request.contextPath}/web/spacelist">申请活动 - 入口</a>
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
