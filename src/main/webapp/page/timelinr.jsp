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
	<link href="${pageContext.request.contextPath}/css/plugins/Timelinr/Timelinr.css" rel="stylesheet">
	<style type="text/css">
		#timeline {width: 940px;height: 440px;overflow: hidden;margin: 40px auto;position: relative;background: url(../images/dot.gif) 220px top repeat-y;}
		#dates {width: 240px;height: 440px;overflow: hidden;float: left;}
		#dates li {list-style: none;width: 220px;height: 100px;line-height: 100px;font-size: 24px; padding-right:20px; text-align:right; background:url(../images/biggerdot.png) 215px center no-repeat;}
		#dates a {line-height: 38px;padding-bottom: 10px;}
		#dates .selected {font-size: 38px;}
		#issues {width: 700px;height: 440px;overflow: hidden;float: right;}
		#issues li {width: 700px;height: 413px;list-style: none;}
		#issues li h1 {color: #ffcc00;font-size: 42px; height:52px; line-height:52px; text-shadow: #000 1px 1px 2px;}
		#issues li p {font-size: 14px;margin: 0px;line-height: 26px;}
	</style>
</head>
<body>
<%@ include file="top.jsp"%>
<article id="timelinr_article">
	<div id="main">
		<div id="timeline">
			<ul id="dates">
				<li><a href="#2019">2019</a></li>
			</ul>
			<ul id="issues">
				<li id="2019">
					<h1>活动分享系统</h1>
					<p>活动分享系统主要实现用户在线参与活动，主要为了解决传统线下预约麻烦的问题。参与者也可以线上组织活动</p>
				</li>

			</ul>
			<a href="#" id="next"></a> <!-- optional -->
			<a href="#" id="prev"></a> <!-- optional -->
		</div>
	</div>
</article>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/timelinr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/Timelinr/jquery.timelinr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/Timelinr/jquery.mousewheel.js"></script>
<script type="text/javascript">
    var userId = ${sessionScope.userId};
    $(function(){
        $().timelinr({
            orientation: 	'vertical',
            issuesSpeed: 	300,
            datesSpeed: 	100,
            arrowKeys: 		'true',
            startAt:		1,
            mousewheel:		'true',
            autoPlay:      true
        });
    });
</script>
</body>
</html>
