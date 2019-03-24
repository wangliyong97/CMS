<%--
  Created by IntelliJ IDEA.
  User: wangliyong
  Date: 2019/2/24
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Activity sharing - 个人设置</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap3.3.7.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/setting.css"  rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/paging.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/page/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 要在最前面引入-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap3.3.7.min.js"></script>
</head>
<body>
<div class="container border" style="min-height: 100%;background-color: white">
    <div class="row" style="padding: 5% 10%;">
        <div class="col-md-3">
            <div class="list-group">
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divBase',this)"
                        style="border: 0;color: orangered;">
                    <img class="img32px" style="opacity: 0.5" src="${pageContext.request.contextPath}/images/page/icons8-tune-80.png">
                    &nbsp;&nbsp;基础设置
                </button>
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divProfile',this)"
                        style="border: 0;">
                    <img class="img32px" style="opacity: 0.5" src="${pageContext.request.contextPath}/images/page/icons8-profile-100.png">
                    &nbsp;&nbsp;个人信息
                </button>
                <button type="button" class="list-group-item btn-item" onclick="showDiv('divAccount',this)"
                        style="border: 0;">
                    <img class="img32px" style="opacity: 0.5" src="${pageContext.request.contextPath}/images/page/icons8-user-80.png">
                    &nbsp;&nbsp;账号
                </button>
            </div>
            <br>
            <div style="padding-left: 16px;">
                <a href="/help-feedback">帮助与反馈</a>
                <hr>
                <small style="color: gray">
                    修改之后记得点击&nbsp;<b>保存</b><br>
                    使修改生效。
                </small>
            </div>
            <br>
            <br>
            <a class="button-save" href="${pageContext.request.contextPath}/index">返回首页</a>
        </div>

        <div class="col-md-9">
            <div id="divBase">
                <img src="${sessionScope.avatar}" class="avatar-img" id="user-photo" >&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-edit-new" data-target="#changeModal" data-toggle="modal">更改头像</button>
                <br>
                <br>
                <ul class="list-group group">
                    <li class="list-group-item" style="border: 0;">
                        <span class="li-title">用户名</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" type="text" value="${sessionScope.username}"
                               id="modifyName">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">昵称</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" value="${sessionScope.nickname}"
                               id="modifyNickname">
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">电子邮件</span>
                        &nbsp;&nbsp;
                        <input class="jianshu-style-input" type="email" value="${sessionScope.email}"
                               id="modifyEmail">
                    </li>
                </ul>
                <button class="button-save" id="settingBtnBase" onclick="saveBaseDiv()">保存</button>
            </div>

            <div style="display: none" id="divProfile">
                <ul class="list-group group">
                    <li class="list-group-item">
                        <span class="li-title">性别</span>
                        &nbsp;&nbsp;
                        <div class="col-sm-10" id="modifyGender" >
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="emp_add_gender1" checked="checked"> 男 </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="emp_add_gender2" checked=""> 女 </label>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <span class="li-title">生日</span>
                        &nbsp;&nbsp;
                        <input type='text' class="jianshu-style-input" id='datetimepicker'/>
                    </li>
                    <li class="list-group-item">
                        <div class="vertical-center">
                            <span class="li-title">个人简介</span>
                            &nbsp;&nbsp;
                            <textarea style="width: 80%" class="jianshu-style-textarea"
                                      id="modifyProfileAboutMe">${sessionScope.intro}</textarea>
                        </div>
                    </li>
                </ul>

                <button class="button-save" id="settingBtnProfile" onclick="saveProfileDiv()">保存</button>
            </div>

            <div style="display: none" id="divAccount">
                <ul class="list-group group">
                    <li>
                        <br>
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading shadow-border-for-setting" role="tab" id="deleteAccountDiv"
                                     style="background-color: transparent;" data-toggle="collapse"
                                     data-parent="#accordion"
                                     href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <h4 class="panel-title">
                                        删除账号
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                                     aria-labelledby="deleteAccountDiv">
                                    <div class="panel-body">
                                        <br>
                                        <h4>永久删除帐号</h4>
                                        <br>
                                        该操作将会清空您参与的所有活动相关数据以及举办的活动信息
                                        &nbsp;<hr class="default-line">
                                        如果你对&nbsp;<i>Activity Sharing</i>&nbsp;的某些内容、功能不满意，你可以在&nbsp;<a
                                            href="/help-feedback">帮助与反馈</a>&nbsp;页向我提出。
                                        <hr class="default-line">
                                        删除帐号是不可逆的操作，删除后将无法恢复。
                                        <br>
                                        <br>
                                        <button class="button-dangerous" data-target="#confirmDialog" data-toggle="modal">删除</button>&nbsp;
                                        <small style="color: darkgray;">期待下次注册</small>
                                        <br>
                                        <br>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="panel panel-default">
                                <div class="panel-heading shadow-border-for-setting" role="tab" id="updatePwd"
                                     style="background-color: transparent;" data-toggle="collapse"
                                     data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                                     aria-controls="collapseTwo">
                                    <h4 class="panel-title">
                                        修改密码
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel"
                                     aria-labelledby="updatePwd">
                                    <div class="panel-body">
                                        <br>
                                        <div class="form-group">
                                            <label>新密码</label><br>
                                            <input type="password" class="form-input" id="newPwd"
                                                   placeholder="输入新密码">
                                        </div>
                                        <div class="form-group">
                                            <label>验证码</label><br>
                                            <div>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <input type="number" class="form-input" id="emailCode" placeholder="验证码"></td>
                                                        <td>&nbsp;&nbsp;
                                                            <button class="button-info" onclick="sendEmailCode()" id="sendPhoneCodeBtn">获取验证码
                                                        </button>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <br>
                                            <button class="button-save" id="saveNewPwd" onclick="updatePwd()">修改
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span class="error-msg" id="errorMsgOperAccount"></span>
                    </li>
                </ul>
            </div>

            <div style="display: none" id="divStatistic">
            </div>
            <br>
            <span class="error-msg" id="settingErrorMsg"></span>
        </div>
    </div>
</div>

<div class="modal fade" id="changeModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title text-primary">
                    <i class="fa fa-pencil"></i>
                    更换头像
                </h4>
            </div>
            <div class="modal-body">
                <p class="tip-info text-center">
                    未选择图片
                </p>
                <div class="img-container hidden">
                    <img src="" alt="" id="photo">
                </div>
                <div class="img-preview-box hidden">
                    <hr>
                    <span>150*150:</span>
                    <div class="img-preview img-preview-lg">
                    </div>
                    <span>100*100:</span>
                    <div class="img-preview img-preview-md">
                    </div>
                    <span>30*30:</span>
                    <div class="img-preview img-preview-sm">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <label class="btn btn-danger pull-left" for="photoInput">
                    <input type="file" class="sr-only" id="photoInput" accept="image/*">
                    <span>打开图片</span>
                </label>
                <button class="btn btn-primary disabled" disabled="true" onclick="sendPhoto();">提交</button>
                <button class="btn btn-close" aria-hidden="true" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    var userId = ${sessionScope.userId};
    // baseSetting
    var name = '${sessionScope.username}';
    var email = '${sessionScope.email}';
    var birthday = '${sessionScope.birthday}';
    var nickName = '${sessionScope.nickname}';
    var birthday = '${sessionScope.birthday}';
    var intro = '${sessionScope.intro}';
    var aboutMe = '${sessionScope.aboutMe}';
    var gender = '${sessionScope.gender}'

    function init(){
        if(gender == 0 || gender == null){
            $('#emp_add_gender1').attr('checked',"checked");
            $('#emp_add_gender2').attr('checked',"");
        }else{
            $('#emp_add_gender1').attr('checked',"");
            $('#emp_add_gender2').attr('checked',"checked");
        }
    }

    var initCropperInModal = function(img, input, modal){
        var $image = img;
        var $inputImage = input;
        var $modal = modal;
        var options = {
            aspectRatio: 1, // 纵横比
            viewMode: 2,
            preview: '.img-preview' // 预览图的class名
        };
        // 模态框隐藏后需要保存的数据对象
        var saveData = {};
        var URL = window.URL || window.webkitURL;
        var blobURL;
        $modal.on('show.bs.modal',function () {
            // 如果打开模态框时没有选择文件就点击“打开图片”按钮
            if(!$inputImage.val()){
                $inputImage.click();
            }
        }).on('shown.bs.modal', function () {
            // 重新创建
            $image.cropper( $.extend(options, {
                ready: function () {
                    // 当剪切界面就绪后，恢复数据
                    if(saveData.canvasData){
                        $image.cropper('setCanvasData', saveData.canvasData);
                        $image.cropper('setCropBoxData', saveData.cropBoxData);
                    }
                }
            }));
        }).on('hidden.bs.modal', function () {
            // 保存相关数据
            saveData.cropBoxData = $image.cropper('getCropBoxData');
            saveData.canvasData = $image.cropper('getCanvasData');
            // 销毁并将图片保存在img标签
            $image.cropper('destroy').attr('src',blobURL);
        });
        if (URL) {
            $inputImage.change(function() {
                var files = this.files;
                var file;
                if (!$image.data('cropper')) {
                    return;
                }
                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {

                        if(blobURL) {
                            URL.revokeObjectURL(blobURL);
                        }
                        blobURL = URL.createObjectURL(file);

                        // 重置cropper，将图像替换
                        $image.cropper('reset').cropper('replace', blobURL);

                        // 选择文件后，显示和隐藏相关内容
                        $('.img-container').removeClass('hidden');
                        $('.img-preview-box').removeClass('hidden');
                        $('#changeModal .disabled').removeAttr('disabled').removeClass('disabled');
                        $('#changeModal .tip-info').addClass('hidden');

                    } else {
                        window.alert('请选择一个图像文件！');
                    }
                }
            });
        } else {
            $inputImage.prop('disabled', true).addClass('disabled');
        }
    }

    var sendPhoto = function () {
        // 得到PNG格式的dataURL
        var photo = $('#photo').cropper('getCroppedCanvas', {
            width: 300,
            height: 300
        }).toDataURL('image/png');

        $.ajax({
            url: '/user/changeAvatar', // 要上传的地址
            type: 'post',
            data: {
                'image': photo,
                'userId' : userId
            },
            dataType: 'json',
            success: function (data) {
                if (data.code == 200) {
                    // 将上传的头像的地址填入，为保证不载入缓存加个随机数
                    $('.user-photo').attr('src', '头像地址?t=' + Math.random());
                    $('#changeModal').modal('hide');
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        });
    }

    $(function(){
        initCropperInModal($('#photo'),$('#photoInput'),$('#changeModal'));
        init();
        $('#datetimepicker').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn')
        });
    });

</script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/paging.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/comm.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/page/setting.js"></script>
</body>
</html>

