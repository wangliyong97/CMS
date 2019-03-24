var globalCount = 0;
$(".top").click(function() {
    $('body,html').animate({
        scrollTop : 0
    }, 1000);
    return false;
});

var count = 1;
var width = window.innerWidth || document.documentElement.clientWidth;

$(window).scroll(function() {
    if ($(document).scrollTop() > 20 && count == 1) {
        $(".dj").css("display", "block");
        initActivityByClick();
        count++;
    }
    if ($(document).scrollTop() > 1200 && width > 700) {
        $(".top").addClass('cd-is-visible fadeIn');
        $(".fixed-menu-list").css("display", "block");
    } else {
        $(".top").removeClass('cd-is-visible fadeOut');
        $(".fixed-menu-list").css("display", "none");
    }
});

$(document).ready(function() {
    if ($(".newsview h1").text() != "404") {
        Format();
        Tags();
        selectPrevActivity();
        selectNextActivity();
        initActivityByRel(); //初始化相关文章
    }
    initActivityByLike();
    setTimeout(function() {
        $(".ds").css("opacity", "1");
    }, 500);
    var topId = $(".nav-item"); /*获取目录点击区域*/
    topId.click(function() {
        var topId = $(this).attr("id");
        $('body,html').animate({
            scrollTop : $(topId).offset().top
        }, 800);
        return false;
    });
});
var Tags = function() {
    var tag = $(".tag").val();
    var keyword = "";
    $(".newsview").find(".tags").html("");
    if (tag != '' && tag != null) {
        if (tag.search(';') != -1) {
            var strs = new Array();
            strs = tag.split(";");
            for (var i = 0; i < strs.length && strs[i] != ''; i++) {
                keyword += '<a href="#">' + strs[i] + '</a>';
            }
        } else {
            keyword = '<a href="#">' + tag + '</a>';
        }
    }
    $(".newsview").find(".tags").append(keyword);
}

var selectPrevActivity = function() {
    var id = $(".id").val();
    var params = {
        id : id - 1
    };
    $.ajax({
        url : '../selectPrevActivity',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var preTitle = "";
            if (data.status == 200) {
                var id = data.activity.id.toString(8) * data.activity.id;
                preTitle = '<a href="../find/' + id + '.html">' + data.activity.title + '</a>';
            } else {
                preTitle = '<span>无</span>';
            }
            $(".pre").html(preTitle);
        },
        error : function() {
            layer.msg('加载的太快啦', {
                icon : 2
            });
        }
    });
};

var selectNextActivity = function() {
    var vid = $(".id").val();
    var id = parseInt(vid) + 1;
    var params = {
        id : id
    };
    $.ajax({
        url : '../selectNextActivity',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var nextTitle = '';
            if (data.status == 200) {
                var sid = data.activity.id.toString(8) * data.activity.id;
                nextTitle = '<a href="../find/' + sid + '.html">' + data.activity.title + '</a>';
            } else {
                nextTitle = '<span>无</span>';
            }
            $(".next").html(nextTitle);
        },
        error : function() {
            layer.msg('加载的太快啦', {
                icon : 2
            });
        }
    });

};

//初始化相关活动
var initActivityByRel = function() {
    var params = {
        pageSize : 6,
        page : 1,
        'type.id' : $(".typeId").val(),
    };
    $.ajax({
        url : '../selectGroupLikeActivityListByPage',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var relActivity = '';
            var data = data.activityList;
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id.toString(8) * data[i].id;
                relActivity += '<li><i style="color:#5788aa" class="fa fa-paperclip"></i> <a href="' + id + '.html" title="' + data[i].title + '">' + data[i].title + '</a></li>'
            }
            // 初始化数据
            $(".otherlink").find("ul").html(relActivity);
        },
        error : function() {
            layer.msg('加载的太快啦', {
                icon : 2
            });
        }
    });
};

//初始化推荐
var initActivityByLike = function() {
    //设置参数
    var params = {
        pageSize : 8,
        page : 1,
        isrecommend : 1 //1 表示推荐
    };
    $.ajax({
        url : '../selectGroupLikeActivityListByPage',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var likeActivity = '';
            var data = data.activityList;
            var time = '';
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id.toString(8) * data[i].id;
                time = i * 0.05;
                likeActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><b><a href="../find/' + id + '.html">'
                    + data[i].title
                    + '</a></b><p><i><img src="' + data[i].images + '"></i><span>'
                    + data[i].introduction + '<span></p></li>'
            }
            // 初始化数据
            $(".paihang").find(".like").html(likeActivity);
        },
        error : function() {
            layer.msg('加载的太快啦', {
                icon : 2
            });
        }
    });
};

//初始化点击排行
var initActivityByClick = function() {
    //设置参数
    var params = {
        pageSize : 8,
        page : 1,
        sort : "clickNum", //按点击量排序,默认按时间
    };
    $.ajax({
        url : '../selectGroupLikeActivityListByPage',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var clickActivity = '';
            var data = data.activityList;
            var time = '';
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id.toString(8) * data[i].id;
                time = i * 0.05;
                clickActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><b><a href="../find/' + id + '.html">'
                    + data[i].title
                    + '</a></b><p><i><img src="' + data[i].images + '"></i><span>'
                    + data[i].introduction + '</span></p></li>'
            }
            // 初始化数据
            $(".paihang").find(".click").html(clickActivity);
        },
        error : function() {
            layer.msg('加载的太快啦', {
                icon : 2
            });
        }
    });
};

//格式化时间
var Format = function() {
    var datetime = $(".addtime").val();
    var month = new Array();
    month["Jan"] = '01';
    month["Feb"] = '02';
    month["Mar"] = '03';
    month["Apr"] = '04';
    month["May"] = '05';
    month["Jun"] = '06';
    month["Jul"] = '07';
    month["Aug"] = '08';
    month["Sep"] = '09';
    month["Oct"] = '10';
    month["Nov"] = '11';
    month["Dec"] = '12';
    var arr = new Array();
    arr = datetime.split(" ");
    var fmt = arr[5] + '-' + month[arr[1]] + '-' + arr[2];
    $(".au02").html(fmt);
}