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
var time = null;
var width = window.innerWidth || document.documentElement.clientWidth;
if (width < 660) {
  var pagenav = '<p style="text-align:center;margin:-5px auto 20px;"><a href="javascript:void(0);" onclick="initActivityByClickMore()"><i class="fa fa-arrow-down"></i> 加载更多</a></p>';
  $(".pageMin").html(pagenav);
}

$(window).scroll(
  function() {
    if (isEnd == true) {
      return;
    }
    if ($(document).scrollTop() > 110 && count == 1) {
      //$(".dj").css("display", "block");
      //initActivityByClick(); //初始化点击排行5项活动
      count++;
    }
    if ($(document).scrollTop() > 350 && count == 2) {
      $(".newblogs").css("display", "block");
        initActivityByNew(1); //初始化最新5项活动
      count++;
    }
    if ($(document).scrollTop() > 1050 && width > 700) {
      $(".top").addClass('cd-is-visible fadeIn');
    } else {
      $(".top").removeClass('cd-is-visible fadeOut');
    }
    if ($(document).scrollTop() > 2200 && width > 700) {
      $(".guanzhu").css("display", "block");
    } else {
      $(".guanzhu").css("display", "none");
    }
    if ($(document).scrollTop() + 100 >= $(document).height()
      - $(window).height()
      && width > 700) {
      isEnd = true;
      $('.page').css('display', 'block');
      setTimeout(function() {
         initActivityByNew(pageNext);
      }, 500);
    } else {
      $('.page').css('display', 'none');
    }
  });

$(document).ready(function() {
    initActivityByLikebg(); //初始化滚动图片
    initActivityByTop(); //初始化置顶的3篇文章
    initActivityByAllType();
    initActivityByLike(); //初始化特别推荐6项活动
    initActivityByClick(); //初始化点击排行5篇文章
});

var initActivityByClickMore = function() {
  setTimeout(function() {
      initActivityByNew(pageNext);
  }, 200);
}

var returnAllCount = function() {
  if (globalCount == 2) {
    setTimeout(function() {
      $('article').css('opacity', '1');
    }, 200);
  }
}

var initActivityByTop = function() {
  //设置参数
  var params = {
    pageSize : 5,
    page : 1,
    istop : 1, //1 表示置顶
    status : 1
  };
  $.ajax({
    url : 'selectGroupLikeActivityListByPage',
    type : 'get',
    data : params,
    dataType : 'json',
    success : function(data) {
      var topActivity = '';
      var data = data.activityList;
      for (var i = 0; i < data.length; i++) {
        var id = data[i].id.toString(8) * data[i].id;
          topActivity += '<li><a href="find/' + id + '.html" title=' + data[i].title + ' target="_blank">' + data[i].title + '</a></li>';
      }
      // 初始化数据
      $(".notice").find("ul").html(topActivity);
      globalCount++;
      returnAllCount();
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};

//初始化每个类别的前N
var initActivityByAllType= function() {
  $.ajax({
    url : 'selectActivityByAllType',
    type : 'get',
    dataType : 'json',
    success : function(data) {
      var likeActivity = '';
      var data = data.activityMap;
      var tab_button = "";
      var newsitem = "";
      var indexTab = 0;
      for (var type in data) {
        if (indexTab == 0) {
          tab_button += "<li class='newscurrent'>" + type + "</li>"
        } else {
          tab_button += "<li>" + type + "</li>"
        }
        indexTab++;
      }
      $(".tab_buttons ul").html(tab_button);
      var index = 0;
      for (var type in data) {
        var newspic = "";
        var newslist = "";
        if (index == 0) {
          newsitem += "<div class='newsitem' style='display: block;'><div class='newspic'><ul>";
        } else {
          newsitem += "<div class='newsitem' style='display: none;'><div class='newspic'><ul>";
        }
        index++;
        newslist += "<ul class='newslist'>"
        for (var i = 0; i < data[type].length; i++) {
          var id = data[type][i].id.toString(8) * data[type][i].id;
          if (i < 2) {
            newspic += "<li><a href=find/" + id + ".html target='_blank'><img src=" + data[type][i].images + "> <span>" + data[type][i].title + "</span></a></li>";
          }
          if (i >= 1) {
            newslist += "<li><i></i><a href=find/" + id + ".html target='_blank'>" + data[type][i].title + "<p>" + data[type][i].introduction + "</p></a></li>";
          }
        }
        newspic += "</ul></div>"
        newsitem += newspic;
        newsitem += newslist;
        newsitem += "</ul></div>"
      }
      $('.newstab').html(newsitem);
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};

//初始化推荐
var initActivityByLike = function() {
  //设置参数
  var params = {
    pageSize : 6,
    page : 1,
    isrecommend : 1, //1 表示推荐
    status : 1
  };
  $.ajax({
    url : 'selectGroupLikeActivityListByPage',
    type : 'get',
    data : params,
    dataType : 'json',
    success : function(data) {
      var likeActivity = '';
      var data = data.activityList;
      for (var i = 0; i < data.length; i++) {
        var id = data[i].id.toString(8) * data[i].id;
        var time = i * 0.05;
          likeActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><i class="ztpic"><a target="_blank" href="find/' + id + '.html" ><img src="' + data[i].images + '"></a></i><b>'
          + data[i].title
          + '</b><span>'
          + data[i].introduction
          + '</span><a href="find/' + id + '.html" target="_blank" class="readmore">阅读原文</a></li>'
      }
      // 初始化数据
      $(".zhuanti").find("ul").html(likeActivity);
      globalCount++;
      returnAllCount();
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};

//初始化最新活动
var initActivityByNew = function(page) {
  //设置参数
  var params = {
    pageSize : 5,
    page : page,
    status : 1
  };
  $
    .ajax({
      url : 'selectGroupLikeActivityListByPage',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(dataAll) {
        var newActivity = '';
        var parm = "";
        var arr = new Array();
        var data = dataAll.activityList;
        var page = dataAll.pageInfo;
        for (var i = 0; i < data.length; i++) {
          arr[i] = data[i].id;
          parm += data[i].id + ",";
          var id = data[i].id.toString(8) * data[i].id;
          var keyword = "";
          if (data[i].keyword != ""
            && data[i].keyword != null) {
            if (data[i].keyword.search(';') != -1) {
              keyword = data[i].keyword.replace(/;/g,
                "|");
            } else {
              keyword = data[i].keyword;
            }
          }
            newActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeInDown"><h3 class="blogtitle"><a target="_blank" href="find/' + id + '.html"  >'
            + data[i].title
            + '</a></h3><span class="blogpic imgscale"><a href="find/' + id + '.html" title=""><img src="' + data[i].images + '"  /></a></span><p class="blogtext">'
            + data[i].introduction
            + '</p><p class="bloginfo"><i class = "avatar"><img src="images/image_.jpg" border=0 width="30" height="30"></i><span>luotf</span><span><a href="javascript:void(0);">【'
            + keyword
            + '】</a></span><span class="m_time">'
            + Format(data[i].addtime, "yyyy-MM-dd")
            + '</span><span  class="clicknum">浏览('
            + data[i].clicknum
            + ')</span><span class="f_r"></p><a href="find/' + id + '.html" class="viewmore">查看详情</a></span></li>'
        }
        var p = {
          client_id : 'cytzg9rLH',
          topic_source_id : parm
        };
        $
          .ajax({
            url : 'http://changyan.sohu.com/api/2/topic/count',
            type : 'get',
            data : p,
            dataType : 'jsonp',
            success : function(pl) {
              for (var i = 0; i < arr.length; i++) {
                $('.' + arr[i])
                  .html(
                    pl.result[arr[i]].comments);
              }
            },
            error : function() {
              layer.msg('出错啦', {
                icon : 2
              });
            }
          });
        // 初始化数据
        if (page.pageNum >= 2) {
          $(".newblogs").find("ul").append(newActivity);
        } else {
          $(".newblogs").find("ul").html(newActivity);
        }
        if (page.total > 5) {
          var pagenav = '';
          if (page.pageNum == page.pages) {
            isEnd = true;
            pagenav = '<p style="text-align:center;margin:-5px auto 10px;"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p>';
            if (width < 660) {
              $(".pageMin").html(pagenav);
            }
          } else {
            isEnd = false;
            pageNext = page.pageNum + 1;
            pagenav = '<div style="margin:-5px auto 10px;text-align:center;"><div class="loader-inner ball-pulse"><div></div><div></div><div></div></div></div>';
          }
          $(".page").html(pagenav);
        } else {
          $(".page").html("");
        }

      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
};

//初始化点击排行
var initActivityByClick = function() {
  //设置参数
  var params = {
    pageSize : 15,
    page : 1,
    sort : "clickNum", //按点击量排序,默认按时间
    status : 1,
  };
  $
    .ajax({
      url : 'selectGroupLikeActivityListByPage',
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
            clickActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><b><a target="_blank" href="find/' + id + '.html">'
            + data[i].title
            + '</a></b><p><i><img src="' + data[i].images + '"></i><span>'
            + data[i].introduction + '</span></p></li>'
        }
        // 初始化数据
        $(".paihang").find("ul").html(clickActivity);
      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
};

//初始化推荐背景
var initActivityByLikebg = function() {
    var params = {
        pageSize : 4,
        page : 1,
        isrecommend : 1 //1 表示推荐
    };
    $.ajax({
        url : 'selectGroupLikeActivityListByPage',
        type : 'get',
        data : params,
        dataType : 'json',
        success : function(data) {
            var likeActivity = '';
            var data = data.activityList;
            var time = '';
            var id = '';
            //用于处理图片标题问题
            if(data.length > 0){
                id = data[0].id.toString(8) * data[0].id;
                time = 0 * 0.03;
                likeActivity = '<i><a target="_blank" href="find/' + id + '.html">' + '<img src="' + data[0].images + '"></i></a>'
                // 初始化数据
                $(".carousel-inner").find(".tupian1").html(likeActivity);
                if(data.length - 1 > 0){
                    id = data[1].id.toString(8) * data[1].id;
                    time = 1 * 0.03;
                    likeActivity = '<i><a target="_blank" href="find/' + id + '.html">' + '<img src="' + data[1].images + '"></i></a>'
                    // 初始化数据
                    $(".carousel-inner").find(".tupian2").html(likeActivity);
                    if(data.length - 2 > 0){
                        id = data[2].id.toString(8) * data[2].id;
                        time = 2 * 0.03;
                        likeActivity = '<i><a target="_blank" href="find/' + id + '.html">' + '<img src="' + data[2].images + '"></i></a>'
                        // 初始化数据
                        $(".carousel-inner").find(".tupian3").html(likeActivity);
                        if(data.length - 3 > 0){
                            id = data[3].id.toString(8) * data[3].id;
                            time = 3 * 0.03;
                            likeActivity = '<i><a target="_blank" href="find/' + id + '.html">' + '<img src="' + data[3].images + '"></i></a>'
                            // 初始化数据
                            $(".carousel-inner").find(".tupian4").html(likeActivity);
                        }
                    }
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
    "S" : datetime.getMilliseconds()
  //毫秒   
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
      .substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1,
        (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
          .substr(("" + o[k]).length)));
  return fmt;
}