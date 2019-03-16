//切换展示页面
var currentShowDiv = 'divBase';

function showDiv(id, th) {

    var btn = $('.btn-item');
    for (var index in btn) {
        $(btn[index]).css('color', 'gray');
    }

    $(th).css('color', 'orangered');

    if (currentShowDiv !== id) {
        $('#' + currentShowDiv).fadeOut("fast", function () {
            $('#' + id).slideDown('fast');
            currentShowDiv = id;
        });
    }

}

var maxIntroCount = 25;
var maxAboutMeCount = 180;

function saveProfileDiv() {
    var mIntro = $('#modifyProfileIntro').val();
    var mAboutMe = $('#modifyProfileAboutMe').val();
    var btnId = 'settingBtnProfile';

    if (mIntro !== intro || mAboutMe !== aboutMe) {

        // 检查 intro 字数
        if (mIntro.length > maxIntroCount) {
            error('主页 title 字数不能超过 ' + maxIntroCount + ' 个，当前 '
                + mIntro.length + ' 字', 'settingErrorMsg', true, 3000);
            return;
        }

        // 检查 aboutMe 字数
        if (mAboutMe.length > maxAboutMeCount) {
            error('博主自述字数不能超过 ' + maxAboutMeCount + ' 个，当前 '
                + mAboutMe.length + ' 字', 'settingErrorMsg', true, 3000);
            return;
        }

        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/blogger/' + bloggerId + '/profile',
            data: {
                intro: mIntro,
                aboutMe: mAboutMe
            },
            async: false,
            type: 'put',
            success: function (result) {
                if (result.code === 0) {
                    disableButton(false, btnId, '修改成功', "button-disable");
                    toast('修改成功', 1000);
                    setTimeout(function () {
                        disableButton(true, btnId, '保存', "button-disable");
                    }, 1000);

                    intro = mIntro;
                    aboutMe = mAboutMe;
                } else {
                    error(result.msg, 'settingErrorMsg', true, 3000);
                }
            }
        });

    } else {
        error('未修改', 'settingErrorMsg', true, 3000);
    }

}

function saveBaseDiv() {
    var mName = $('#modifyName').val();
    var mEmail = $('#modifyEmail').val();
    var mNickname = $('#modifyNickname').val();
    var btnId = 'settingBtnBase';

    var nameModify = false;
    var editSucc = false;

    // 若用户名修改发起 Account修改请求
    // 修改用户名需要刷新网页
    if (mName !== name) {
        disableButton(false, btnId, '正在修改...', "button-disable");
        $.ajax({
            url: '/user/item=name',
            type: 'post',
            async: false,
            data:
                {
                    userId: userId,
                    username: mName
                },
            dataType: 'json',
            success: function (data) {
                if(data.code == 0){
                    name = mName;
                    nameModify = true;
                    editSucc = true;
                }
            },
            error : function ( ) {
                editSucc = false;
                error(result.msg, 'settingErrorMsg', true, 3000);
            }
        });
    }

    // 若nickName修改发起 Account修改请求
    if (mNickname !== nickName) {
        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/user/item=nickname',
            type: 'post',
            async: false,
            data:
                {
                    userId: userId,
                    nickname: mNickname
                },
            dataType: 'json',
            success: function (data) {
                if(data.code == 0){
                    nickName = mNickname;
                    nameModify = true;
                    editSucc = true;
                }
            },
            error : function ( ) {
                editSucc = false;
                error(result.msg, 'settingErrorMsg', true, 3000);
            }
        });
    }

    // 若email修改发起 Account修改请求
    if (mEmail !== email) {
        disableButton(false, btnId, '正在修改...', "button-disable");

        $.ajax({
            url: '/user/item=email',
            type: 'post',
            async: false,
            data:
                {
                    userId: userId,
                    email: mEmail
                },
            dataType: 'json',
            success: function (data) {
                if(data.code == 0){
                    email = mEmail;
                    nameModify = true;
                    editSucc = true;
                }
            },
            error : function ( ) {
                editSucc = false;
                error(result.msg, 'settingErrorMsg', true, 3000);
            }
        });
    }


    if (editSucc) {
        disableButton(false, btnId, '修改成功', "button-disable");
        // toast('修改成功', 1000);
        setTimeout(function () {
            disableButton(true, btnId, '保存', "button-disable");
            if (nameModify)
                location.href = '/setting';
        }, 1000);
    } else {
        error('未修改', 'settingErrorMsg', true, 3000);
    }
}


function initImportBlogListener() {
    $('#fileUploadDialog').on('hidden.bs.modal', function (e) {
        $('#progressbar').css('width', '0%');
        $('#progressbar').removeClass('active');
        $('#processStatus').html('');
        $('#importSucc').html('');
        $('#showChoosedFileName').html('');
        $('#zipFile').val('');

    });
}

function confirmExe() {

    disableButton(false, 'confirmBtn', '正在删除...', 'button-disable');

    $.ajax({
        url: '/blogger/' + bloggerId,
        async: false,
        type: 'delete',
        success: function (result) {
            if (result.code === 0) {
                disableButton(false, 'confirmBtn', '删除成功', 'button-disable');

                setTimeout(function () {
                    location.href = '/register';
                }, 1000);

            } else {
                error(result.msg, 'confirmErrorMsg', true, 3000);
                disableButton(true, 'confirmBtn', '删除', 'button-disable');
            }
        }
    });
}

// 发送短信验证码
function sendPhoneCode() {

    if (!isPassword($('#newPwd').val())) {
        error('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>', 'errorMsgOperAccount', true, 2000);
        return;
    }

    createPhoneCode();

    // 10分钟后验证码失效
    setTimeout(function () {
        phoneCode = null;
    }, 10 * 60 * 1000);

    $.post(
        '/sms',
        {
            phone: phone,
            content: '【BLOG】 你的验证码是: ' + phoneCode + ' ,此验证码用于重置登录密码，10分钟内有效。'
        },
        function (result) {
            if (result.code === 0) {
                countDown(60, 1000, function (c) {
                    if (c === 0) {
                        return true;
                    } else {
                        disableButton(false, 'sendPhoneCodeBtn', c + ' 秒后重新发送', "button-info-disable");
                        return false;
                    }
                });
            } else {
                error('验证码无法发送', 'errorMsgOperAccount', true, 3000);
            }
        }, 'json');
}

// 短信验证码
var phoneCode;

function createPhoneCode() {
    var code = '';
    for (var i = 0; i < 6; i++) {
        var n = Math.floor(Math.random() * 10);//输出1～10之间的随机整数
        code += n;
    }
    phoneCode = code + '';
}

function initDeleteAccountConfirmDialog() {
    $('#confirmText').html('确认永久删除账号');
}

function updatePwd() {
    var code = $('#phoneCode').val();
    if (isStrEmpty(code)) {
        error('请输入验证码', 'errorMsgOperAccount', true, 3000);
        return;
    }

    if (code !== phoneCode) {
        error('验证码错误', 'errorMsgOperAccount', true, 3000);
        return;
    }
}


$(document).ready(function () {
    initImportBlogListener();

    initDeleteAccountConfirmDialog()
});

