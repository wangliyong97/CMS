<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/2/18
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导航栏</title>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="shortcut icon">
    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/m.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/cl-css.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/highlight/styles/railscasts.css"rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/highlight/highlight.pack.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<header id="header">
    <div class="cl-header container-fluid">
        <div class="header-content container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand" href="/">
                    <img src="${pageContext.request.contextPath}/images/logo.jpg" width="144" height="72" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/index">首页 <span class="sr-only">(current)</span></a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/list">活动介绍</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/resource">资源分享</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/about">关于我</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/message">留言</a></li>
                        <li class="searchico"></li>
                    </ul>
                </div>
                <div class="collapse navbar-collapse justify-content-end loginOrRegist" id="loginOrRegist">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/webLogin">登录</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">注册</a></li>
                    </ul>
                </div>
                <div class="right" style="float: right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"
                               data-toggle="dropdown"
                               style="height: 60px">
                                <img id="avator" alt="" class="img-circle" src="${pageContext.request.contextPath}/images/person.png" width="40px" height="40px" margin-left="50px"/>
                                <%--<span style="color: #FFFFFF;font-size: 15px">--%>
                                <%--<i>username</i>--%>
                            </span>
                            </a>
                            <div class="dropdown-menu pull-right"
                                 style="background: #FFFFFF;width: 320px;overflow: hidden">
                                <div style="margin-top: 16px;border-bottom: 1px solid #eeeeee">
                                    <div style="text-align: center">
                                        <img id = "avatar_second" class="img-circle" src="${pageContext.request.contextPath}/images/person.png"
                                             style="width: 40px;height: 40px;"/>
                                    </div>
                                    <div style="color: #323534;text-align: center;line-height: 36px;font-size: 15px">
                                        <span id = "spanusername">未登录</span>
                                    </div>
                                </div>

                                <div class="row" style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-user" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            个人中心</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-gear" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            账号管理</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-key" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            密码修改</p>
                                    </div>
                                </div>

                                <div class="row" style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-user-circle" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            修改头像</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-comments" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            消息</p>
                                    </div>
                                    <div class="col-md-4 text-center grid">
                                        <i class="fa fa-heart-o" style="font-size: 25px;line-height: 45px;"></i>
                                        <p style="padding: 0px;margin-top: 6px;margin-bottom: 10px;font-size: 12px">
                                            帮助中心</p>
                                    </div>
                                </div>

                                <div class="row" style="margin-top: 20px">
                                    <div class="text-center"
                                         style="padding: 15px;margin: 0px;background: #f6f5f5;color: #323534;">
                                        <i class="fa fa-sign-out"></i> 退出登入界面
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>
<div class="searchbox">
    <div class="search">
        <form action="${pageContext.request.contextPath}/result" name="searchform" method="post" id="searchform">
            <input name="keyboard" id="keyboard" class="input_text"
                   value="请输入关键字词" style="color: rgb(153, 153, 153);"
                   onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}"
                   onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}"
                   type="text"> <input name="Submit" class="input_submit" value="搜索" type="submit">
        </form>
    </div>
    <div class="searchclose"></div>
</div>

<script type="text/javascript" color="255,140,0" opacity='0.7' zIndex="-1" count="99" src="//cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".dropdown").mouseover(function () {
            $(this).addClass("open");
        });

        $(".dropdown").mouseleave(function () {
            $(this).removeClass("open");
        })
    })

//初始化所有类别信息
    var initAllActivityType = function() {
        //查询出活动类别
        //设置参数，表示查询所有的类别
        var params = {
            "data" : "all"
        };
        $.ajax({
            url : '${pageContext.request.contextPath}/selectActivityType',
            type : 'post',
            data : params,
            dataType : 'json',
            success : function(data) {
                var typeName = '';
                for (var i = 0; i < data.length; i++) {
                    typeName += "<li><a href='${pageContext.request.contextPath}/result?keyboard=type_"+data[i].id +"'>"+ data[i].typename + "</a></li>";
                }
                $(".sub").html(typeName);
            },
            error : function() {
                layer.msg('请求太快，请稍后再试！', {
                    icon : 5
                });
            }
        });
    }
    jQuery(document).ready(function($) {
        initAllActivityType();
    });
</script>
<script type="text/javascript">
    function judgeSession()
    {
        var isLogin = '<%=(String)session.getAttribute("username")%>';
        var avatarUrl = '<%=(String)session.getAttribute("avatar")%>';
        if(isLogin != null && isLogin != "" && isLogin != "null"){
            document.getElementById("loginOrRegist").style.visibility="hidden";
            $("#spanusername").text(isLogin);
        }
        if(avatarUrl != null && avatarUrl != "null" && avatarUrl != ""){
            $("#avator").attr('src',avatarUrl);
            $("#avatar_second").attr('src',avatarUrl);
        }


    }
    Window.onload = judgeSession();
</script>