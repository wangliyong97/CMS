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

var maxAboutMeCount = 180;

//保存基本信息
function saveProfileDiv() {
    var mIntro = $('#modifyProfileAboutMe').val();
    var mBirthday = $('#datetimepicker').val();
    var mGender = gender;
    var radios = document.getElementsByName('gender');
    for(var i = 0 ; i < radios.length ; i++ ){
        if(radios[i].checked == true){
            mGender = i;
        }
    }

    var btnId = 'settingBtnProfile';

    if (mIntro != intro || mBirthday != birthday || mGender != gender) {
        // 检查 aboutMe 字数
        if (mIntro.length > maxAboutMeCount) {
            error('自述字数不能超过 ' + maxAboutMeCount + ' 个，当前 '
                + mIntro.length + ' 字', 'settingErrorMsg', true, 1000);
            return;
        }
        disableButton(false, btnId, '正在修改...', "button-disable");
        $.ajax({
            url: '/user/item=profile',
            type: 'post',
            async: false,
            data:
                {
                    userId : userId,
                    intro : mIntro,
                    birthday : mBirthday,
                    gender : mGender
                },
            dataType: 'json',
            success: function (result) {
                if (result.code === 0) {
                    gender = mGender;
                    intro = mIntro;
                    birthday = mBirthday;
                    init();
                    disableButton(false, btnId, '修改成功', "button-disable");
                    setTimeout(function () {
                        disableButton(true, btnId, '保存', "button-disable");
                    }, 1000);
                } else {
                    error(result.msg, 'settingErrorMsg', true, 1000);
                }
            }
        });

    } else {
        error('未修改', 'settingErrorMsg', true, 1000);
    }
}

//保存个人信息
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

//删除账号
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

//发送邮箱验证码
function sendEmailCode() {
    if (!isPassword($('#newPwd').val())) {
        error('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>', 'errorMsgOperAccount', true, 2000);
        return;
    }
    createEmailCode();
    // 10分钟后验证码失效
    setTimeout(function () {
        emailCode = null;
    }, 10 * 60 * 1000);

    countDown(60, 1000, function (c) {
        if (c === 0) {
            disableButton(true, 'sendPhoneCodeBtn', '获取验证码', "button-info-disable");
            return true;
        } else {
            disableButton(false, 'sendPhoneCodeBtn', c + ' 秒后重新发送', "button-info-disable");
            return false;
        }
    });

    $.ajax({
        url: '/user/sendEmail',
        type: 'post',
        data:
            {
                email : email,
                code : emailCode
            },
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                error('验证码无法发送', 'errorMsgOperAccount', true, 3000);
            }
        }
    });
}

// 邮箱验证码
var emailCode;
function createEmailCode() {
    var code = '';
    for (var i = 0; i < 6; i++) {
        var n = Math.floor(Math.random() * 10);//输出1～10之间的随机整数
        code += n;
    }
    emailCode = code + '';
}

function initDeleteAccountConfirmDialog() {
    $('#confirmText').html('确认永久删除账号');
}

//修改密码
function updatePwd() {
    var code = $('#emailCode').val();
    if (isStrEmpty(code)) {
        error('请输入验证码', 'errorMsgOperAccount', true, 2000);
        return;
    }else if (code !== emailCode) {
        error('验证码错误', 'errorMsgOperAccount', true, 2000);
        return;
    }else{
        var newPwd = $('#newPwd').val();
        $.ajax({
            url: '/user/item=password',
            type: 'post',
            async: false,
            data:
                {
                    userId : userId,
                    newPwd : newPwd
                },
            dataType: 'json',
            success: function (result) {
                if (result.code == 200) {
                    disableButton(false, 'saveNewPwd', '修改成功', "button-disable");
                    setTimeout(function () {
                        document.getElementById("newPwd").value = "";
                        document.getElementById("emailCode").value = "";
                        disableButton(true, 'saveNewPwd', '修改', "button-disable");
                    }, 1000);
                } else {
                    error(result.msg, 'settingErrorMsg', true, 1000);
                }
            }
        });
    }
}

$(document).ready(function () {
    init();
    initDeleteAccountConfirmDialog()
});

