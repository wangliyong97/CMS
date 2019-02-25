<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>申请举办活动</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/css/github-gist.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/webuploader/webuploader-demo.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/fakeLoader.css" rel="stylesheet">
</head>
<body>
<%@ include file="top.jsp"%>
    <article>
        <div class="col-sm-9">
            <div class="ibox float-e-margins">
                <div class="mail-box-header">
                    <div class="pull-right tooltip-demo">
                        <button id="add_draft" type="button" class="btn btn-white btn-sm"
                                data-toggle="tooltip" data-placement="top" title="存为草稿">
                            <i class="fa fa-pencil"></i> 存为草稿
                        </button>
                        <button type="button" onclick="" class="btn btn-danger btn-sm"
                                data-toggle="tooltip" data-placement="top" title="放弃">
                            <i class="fa fa-times"></i> 放弃
                        </button>
                    </div>
                    <h2>编辑活动</h2>
                </div>
                <div class="mail-box">
                    <div class="mail-body">
                        <form class="form-horizontal" method="post" id="commentForm">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">封面：</label>
                                <div class="col-sm-10">
                                    <a class="fancybox picPath" href="#pic" data-toggle="modal"
                                       onclick="findPicList()"> <img
                                            class="picPath animated fadeInRight"
                                            style="width: 190px; height: 115px;" alt="封面" title="点击更换封面"
                                            src="/upload/background/2018-05-17334750.JPG" />
                                    </a>
                                </div>
                            </div>
                            <input type="hidden" value="" class="imagePath">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="title"
                                           name="title" value="" required="" maxlength="50"
                                           aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">摘要：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="introduction"
                                           name="introduction" value="" required="" maxlength="150"
                                           aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">类别：</label>
                                <div class="col-sm-10">
                                    <select class="form-control m-b" id="typeName" name="typeName">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">关键字：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="keyword"
                                           required="" aria-required="true" name="keyword" value=""
                                           maxlength="30">
                                    <p class="help-block m-b-none">
                                        <i class="fa fa-info-circle"></i> 多个关键字之间用“;”分隔
                                    </p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="mail-text h-200" style="width:84%;margin:0 auto;">
                        <div id="summernote"></div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="mail-body text-right tooltip-demo">

                        <button id="prev1" type="button" class="btn btn-sm btn-primary"
                                data-placement="top" title="预览" data-toggle="tooltip">
                            <i class="fa fa-reply"></i> 预览
                        </button>

                        <button id="prev2" type="button" style="display:none"
                                onclick="prevBlog()" data-target="#myModal" data-toggle="modal">
                        </button>
                        <button id="add_draft2" type="button"
                                class="btn btn-white btn-sm" data-toggle="tooltip"
                                data-placement="top" title="存为草稿">
                            <i class="fa fa-pencil"></i> 存为草稿
                        </button>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </article>
    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- Web Uploader -->
    <script type="text/javascript">
        // 添加全局站点信息
        var BASE_URL = '${pageContext.request.contextPath}/js/plugins/webuploader';
    </script>
    <script src="${pageContext.request.contextPath}/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/webuploader/webuploader-demo2.js"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${pageContext.request.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/validate/form-validate-demo.js"></script>

    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/js/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/fakeLoader.min.js"></script>

    <!-- SUMMERNOTE -->
    <script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/activity/addActivity.js"></script>
</body>
</html>

