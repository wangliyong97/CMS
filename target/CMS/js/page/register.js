/**
 * Created by wangliyong on 2019/2/23.
 */
function nextStep() {
    if (checkInputAccount()) {
        $('#nextStep').on('click', null, null, function () {
            if (checkInputProfile() && $('#nextStep').attr('disable') !== 'disable') {

                $('#nextStep').css('display', 'none');
                $('#nextStep').attr('disable', 'disable');

                $('#inputAccount').css('display', 'none');
                $('#inputProfile').css('display', 'none');
                $('#inputFinish').css('display', 'block');

                $('#stepTitle3').removeClass('step-title');
                $('#stepTitle3').addClass('step-title-choose');
                $('#stepTitle3_').removeClass('step');
                $('#stepTitle3_').addClass('step-choose');

                register();
            }
        });

        $('#inputAccount').css('display', 'none');
        $('#inputProfile').css('display', 'block');
        $('#inputFinish').css('display', 'none');

        $('#stepTitle2').removeClass('step-title');
        $('#stepTitle2').addClass('step-title-choose');
        $('#stepTitle2_').removeClass('step');
        $('#stepTitle2_').addClass('step-choose');


    }

}

// 为 '' 返回true
function checkInputEmptyWhenRegister(id) {
    var va = $('#' + id);
    if (va.val() === '') {
        errorInfoWhenRegister('<small>请输入&nbsp;</small>' + va.attr('placeholder'));
        return true;
    } else {
        errorInfoWhenRegister('');
        return false;
    }
}

function checkInputAccount() {
    if (checkInputEmptyWhenRegister('registerUserName') ||
        checkInputEmptyWhenRegister('registerPassword') ||
        checkInputEmptyWhenRegister('conformPassword')) {
        return false;
    }

    // 检查密码格式规范
    var pwd = $('#registerPassword').val();
    if (!isPassword(pwd)) {
        errorInfoWhenRegister('密码格式不正确，<small>密码由 6-12 位字母和数字组成</small>');
        return false;
    }

    // 检查两次密码是否一致
    var pwdc = $('#conformPassword').val();
    if (pwd !== pwdc) {
        errorInfoWhenRegister('两次密码不一致');
        return false;
    }

    // 检查用户名是否存在
    $.ajax({
        url: '/user/checkUsername?username=' + $('#registerUserName').val(),
        async: false,
        success: function (result) {
            if (result.code == 200) {
                errorInfoWhenRegister('用户名已被占用');
            } else {
                errorInfoWhenRegister('');
            }
        }
    });

    if ($('#registerErrorMsg').html() === '') return true;
    else return false;
}

var maxIntroCount = 50;
var maxNickNameCount = 10;

function checkInputProfile() {
    if (checkInputEmptyWhenRegister('registerNickName') ||
        checkInputEmptyWhenRegister('registerEmail') ||
        checkInputEmptyWhenRegister('registerIntro')) {
        return false;
    }

    // 检查 intro 字数
    var intro = $('#registerIntro').val();
    if (intro.length > maxIntroCount) {
        error('个人介绍字数不能超过 ' + maxIntroCount + ' 个，当前 '
            + intro.length + ' 字', 'registerErrorMsg', true, 3000);
        return false;
    }

    // 检查 nickname 字数
    var nickName = $('#registerNickName').val();
    if (intro.length > maxIntroCount) {
        error('个人昵称不能超过 ' + maxIntroCount + ' 个，当前 '
            + intro.length + ' 字', 'registerErrorMsg', true, 3000);
        return false;
    }

    // 正则校验邮箱
    var email = $('#registerEmail').val();
    if (!isEmail($('#registerEmail').val())) {
        errorInfoWhenRegister('邮箱格式不正确');
        return false;
    }

    //检查邮箱重复
    $.ajax({
        url: '/user/checkEmail?email=' + email,
        async: false,
        success: function (result) {
            if (result.code == 200) {
                errorInfoWhenRegister('该邮箱已经被注册');
            } else {
                errorInfoWhenRegister('');
            }
        }
    });

    if ($('#registerErrorMsg').html() === '') return true;
    else return false;
}


function errorInfoWhenRegister(msg) {
    error(msg, 'registerErrorMsg', true, 3000);
}

function register() {
    var userName = $('#registerUserName').val();
    var password = $('#registerPassword').val();
    var nickname = $('#registerNickName').val();
    var email = $('#registerEmail').val();
    var intro = $('#registerIntro').val();

    info('正在注册...');

    var params = {
        username: userName,
        password: password,
        nickname: nickname,
        email: email,
        intro: intro
    };
    $.ajax({
        url : '/user/register',
        type : 'post',
        data : params,
        dataType : 'json',
        success : function(data) {
            countDown(3, 1000, function (c) {
                if (c === 0) {
                    login();
                    return true;
                } else {
                    info('<small> 注册成功，' + c + '秒后将跳转到首页！</a>');
                    return false;
                }
            });
        },
        error : function() {
            failInfo(data.msg);
        }

    });

    function login() {
        var param = {
            username: userName,
            password: password,
        };
        $.ajax({
            url : '/user/login',
            type : 'post',
            data : param,
            dataType : 'json',
            success : function(data) {
                location.href = '/'+data.msg;
            },
            error : function() {
                failInfo(data.msg);
                setTimeout(location.href = '/'+data.msg,2000);
            }

        });
    }

    function info(info) {
        $('#finalInfo').html('&nbsp;&nbsp;' + info);
    }

    function failInfo(info) {
        $('#finalInfo').html('&nbsp;&nbsp;<small>注册失败，' + info + '</small>，<a href="/page/register.jsp">重试</a>');
    }

}

// ----------------------------- 登录对话框回调
function funAfterLoginSuccess(result, name) {
    location.href = '/' + name + '/archives';
}

function funAfterLoginFail(result) {
}
