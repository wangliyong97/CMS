<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${activity.title}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <meta name="keywords" content="${activity.keyword}" />
    <meta name="description" content="${activity.introduction}" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/github-gist.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/subscribe.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <div class="lbox">
        <div class="infos">
            <div class="newsview ">
                <h2 class="intitle">
                    您现在的位置是：<a href="/">网站首页</a>&nbsp;&gt;&nbsp;<a href="/">活动简介</a>
                </h2>
                <c:choose>
                    <c:when test="${status== '0' || status== '500'}">
                        <h1 style="font-size:110px;text-align:center;margin:20px;">404</h1>
                        <h3 style="text-align:center;" class="font-bold">抱歉，你所访问的页面不存在~</h3>
                        <h4 style="margin-top:55px;text-align:center;">
                            <a
                                    style="background-color: #676a6c;padding: 5px 10px;color: #fff;border-radius: 10px;"
                                    href="${pageContext.request.contextPath}/index.jsp">去首页</a>
                        </h4>
                    </c:when>
                    <c:otherwise>
                        <h3 class="news_title animated fadeIn">${activity.title}</h3>
                        <input class="id" type="hidden" value="${activity.id}">
                        <div class="news_author animated fadeIn">
                            <span class="au01 ">${user.nickname}</span><span class="au02">
                  <input class="addtime" type="hidden"
                         value="${activity.addtime}">
                </span><span class="au03">共<b>${activity.clicknum}</b>人围观
                </span>
                        </div>
                        <input class="typeId" type="hidden"
                               value="${activity.type.id}">
                        <div class="tags animated fadeIn">
                            <input class="tag" type="hidden" value="${activity.keyword}">
                        </div>
                        <div class="news_about animated fadeIn">
                            <strong>简介</strong>${activity.introduction}</div>
                        <div class="news_infos animated fadeIn">${activity.content}</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="subscribe" style="text-align: center">
                <p><a href="javascript:void(0)" id="subscribeButton" onclick="subscribeToggle()" class="subscribe_button" title="订阅一下">订阅</a></p>
                <div class="hide_box"></div>
                <div class="subscribe_box">
                    <a class="subscribe_close" href="javascript:void(0)" onclick="subscribeToggle()" title="关闭"><img src="${pageContext.request.contextPath}/images/page/close.jpg" alt="取消" /></a>
                    &nbsp;&nbsp;&nbsp;
                    <div class="subscribe_payimg">
                        <img src="${activity.images}" title="活动图片" />
                    </div>
                    <div class="pay_explain">${activity.title}</div>
                    <li class="subscribe-group-item">
                        <span class="li-title">预约提醒时间：</span>
                        <input type='text' class="jianshu-style-input" id='datetimepicker_subscribe'/>
                        &nbsp;&nbsp;
                        <button class="button-save" id="subscribeActivity" onclick="subscribeActivity()">确认订阅</button>
                    </li>
                    <div class="subscribe_info">
                        <p>感谢您参与活动！祝您生活愉快！</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="nextinfo animated fadeIn">
            <p>
                上一篇：<span class="pre"></span>
            </p>
            <p>
                下一篇：<span class="next"></span>
            </p>
        </div>
        <div class="otherlink animated fadeIn"
             style="padding-bottom:20px;">
            <h2>相关活动</h2>
            <ul>

            </ul>
        </div>
        <div class="news_pl " id="news_pl">
            <h2>活动评论</h2>
            <div style="width:90%;margin: 0 auto;">
                <div id="cyEmoji" role="cylabs" data-use="emoji"
                     sid="${activity.id }"></div>
            </div>
        </div>
    </div>

    <div class="rbox  ">

        <div class="dianji paihang whitebg">
            <h2 class="cloud_hometitle">本栏推荐</h2>
            <ul class="like " style="padding:0px">

            </ul>
        </div>
        <div class="dj dianji paihang whitebg animated fadeInUp"
             style="display:none;animation-delay:0.3s">
            <h2 class="cloud_hometitle">点击排行</h2>
            <ul class="click" style="padding:0px">

            </ul>
        </div>
        <script type="text/javascript" charset="utf-8" src="http://changyan.sohu.com/js/changyan.labs.https.js?appid=cytzg9rLH"></script>
    </div>

    <a href="#" class="top cd-top animated ">Top</a>
</article>

<%@ include file="fonter.jsp"%>
<script src="${pageContext.request.contextPath}/js/page/info.js"></script>
<script src="${pageContext.request.contextPath}/js/highlight.pack.js"></script>
<script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/page/comm.js" type="application/javascript"></script>
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    var activity_id = ${activity.id };
    var user_id = ${sessionScope.userId};
    function  subscribeToggle(){
        $(".hide_box").fadeToggle();
        $(".subscribe_box").fadeToggle();
    }
    $(function(){
        $('#datetimepicker_subscribe').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn')
        });
    });
</script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>
