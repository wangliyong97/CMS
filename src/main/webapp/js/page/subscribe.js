/**
 * Created by wangliyong on 2019/3/27.
 */
var globalCount = 0;
$(".top").click(function() {
    $('body,html').animate({
        scrollTop : 0
    }, 1000);
    return false;
});

var pageNext = 1;
var count = 1;
var isEnd = false;

var width = window.innerWidth || document.documentElement.clientWidth;
if (width < 960) {
    var pagenav = '<p style="text-align:center;margin:-5px auto 20px;"><a href="javascript:void(0);" onclick="initActivity()"><i class="fa fa-arrow-down"></i> 加载更多</a></p>';
    $(".pageMin").html(pagenav);
    $(".tag").css('display', 'none');
}

$(window).scroll(function() {
    if (isEnd == true) {
        return;
    }
    if ($(document).scrollTop() > 200 && count == 1) {
        $(".dj").css("display", "block");
        count++;
    }
    if ($(document).scrollTop() > 1300 && width > 700) {
        $(".top").addClass('cd-is-visible fadeIn');
        $(".guanzhu").css("display", "block");
    } else {
        $(".top").removeClass('cd-is-visible fadeOut');
        $(".guanzhu").css("display", "none");
    }
    if ($(document).scrollTop() + 50 >= $(document).height() - $(window).height() && width > 700) {
        isEnd = true;
        $('.page').css('display', 'block');
        setTimeout(function() {
            initActivityListByPage(pageNext, "none", null);
        }, 500);
    }
});

var initActivity = function() {
    initActivityListByPage(pageNext, "none", null);
}

$(document).ready(function() {
    initActivityListByPage(pageNext, "none", null);
});

var initActivityListByPage = function(pageNum, type_id, typename) {
    //查询订阅活动
    params = {
        userId : userId,
        pageSize : 4,
        page : pageNum
    };
    $.ajax({
        url : 'selectSubscribeActivityList',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var subscribeActivityVos = '';
            var page = data.pageInfo;
            var data = data.subscribeActivityVos;
            if (data.length > 0) {
                var parm = "";
                var arr = new Array();
                for (var i = 0; i < data.length; i++) {
                    arr[i] = data[i].id;
                    parm += data[i].id + ",";
                    var id = data[i].id.toString(8) * data[i].id;
                    var keyword = "";
                    if (data[i].keyword != "" && data[i].keyword != null) {
                        if (data[i].keyword.search(';') != -1) {
                            keyword = data[i].keyword.replace(/;/g, "|");
                        } else {
                            keyword = data[i].keyword;
                        }
                    }

                    var image_path = "";
                    var user_id = data[i].user.id;
                    var nickname = "";
                    var param3 = {
                        id : user_id
                    }
                    $.ajax({
                        url : '/selectUserById',
                        type : 'post',
                        data : param3,
                        async: false,
                        dataType : 'json',
                        success : function(data) {
                            image_path = data.user.picturePath;
                            nickname = data.user.nickname;
                        }
                    });

                    subscribeActivityVos += '<li style="animation-delay:0.' + i + 's" class="animated fadeInDown"><h3 class="activityTitle"><a target="_blank" href="find/' + id + '.html"  >'
                        + data[i].title
                        + '</a></h3><span class="activityPic imgscale"><a href="find/' + id + '.html" title=""><img src="' + data[i].images + '"  /></a></span><p class="activityText">'
                        + data[i].introduction
                        + '</p><p class="activityInfo"><i class = "avatar"><img src="'+image_path+'" border=0 width="30" height="30"></i><span>'+'&nbsp;'+nickname+'</span><span><a href="javascript:void(0);">【'
                        + keyword
                        + '】</a></span><span class="m_time">'
                        + Format(data[i].addtime, "yyyy-MM-dd")
                        + '</span><span  class="clicknum">&nbsp;浏览('
                        + data[i].clicknum
                        + ')</span><span class="f_r"></p><a href="find/' + id + '.html" class="viewmore">查看详情</a>' +
                        '<span  class="reminder_time">&nbsp;预约时间：' + Format(data[i].reminderTime, "yyyy-MM-dd") + '</span>' +
                        '<a class="settingSubscribe" id="settingSubscribeActivty" onclick="">取消订阅</a>' + '</span></li>'
                }
            } else {
                subscribeActivityVos = "<h1 style='font-size:110px;text-align:center;margin:20px;'>~(●'◡'●ノ~</h1><h3 style='text-align:center;' class='font-bold'>抱歉！当前用户尚未订阅活动~~~</h3><h4 style='margin-bottom:110px;margin-top:55px;text-align:center;'><a style='background-color: #676a6c;padding: 5px 10px;color: #fff;border-radius: 10px;' href='index.jsp'>去首页</a></h4>";
            }
            $(".subscribeList").find("ul").append(subscribeActivityVos);
            if (page.total > 8) {
                var pagenav = '';
                if (page.pageNum == page.pages) {
                    isEnd = true;
                    pagenav = '<p style="text-align:center;margin:-5px auto 10px;"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p>';
                    if (width < 960) {
                        $(".pageMin").html(pagenav);
                    }
                } else {
                    isEnd = false;
                    pageNext = page.pageNum + 1;
                    pagenav = '<div style="margin:-5px auto 10px;text-align:center;"><div class="loader-inner ball-pulse"><div></div><div></div><div></div></div></div>';
                }
                $(".page").html(pagenav);
            } else {
                isEnd = true;
                $('.page').css('display', 'block');
                $(".page").html('<p style="text-align:center;margin:-5px auto 10px;"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p>');
                if (width < 960) {
                    $(".pageMin").html("");
                }
            }
        },
        error : function() {
            layer.msg('请求太快，请稍后再试！', {
                icon : 5
            });
        }
    });
};

var search = function() {
    var index = '';
    layer.ready(function() {
        index = layer.load(2, {
            shade : [ 0.1, '#eee' ] //0.1透明度的白色背景
        });
    });
    initActivityListByPage(1, "none", null);
    setTimeout(function() {
        layer.close(index);
    }, 100);
}

//格式化时间
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+" : datetime.getMonth() + 1, //月份
        "d+" : datetime.getDate(), //日
        "h+" : datetime.getHours(), //小时
        "m+" : datetime.getMinutes(), //分
        "s+" : datetime.getSeconds(), //秒
        "q+" : Math.floor((datetime.getMonth() + 3) / 3), //季度
        "S" : datetime.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
