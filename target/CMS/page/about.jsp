<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Activity sharing</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath}/css/animate.css"
		  rel="stylesheet">

</head>
<body>
<%@ include file="top.jsp"%>
<article>
	<div class="lbox">
		<div class="infos">
			<div class="newsview">
				<h2 class="intitle">
					您现在的位置是：<a href="index.jsp">网站首页</a>&nbsp;&gt;&nbsp;<a
						href="about.jsp">关于我</a>
				</h2>
				<div class="news_infos m20 animated fadeIn">

				</div>
			</div>
		</div>
	</div>
	<div class="rbox ">
		<div class="aboutme m20 whitebg">
			<h2 class="htitle">关于我</h2>
			<div class="avatar1 animated fadeIn">
				<img src="${pageContext.request.contextPath}/images/logo.png">
			</div>
			<div class="ab_con animated fadeIn">

			</div>
		</div>
	</div>
</article>
</body>
</html>
