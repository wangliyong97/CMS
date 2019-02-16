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
      initActivityByClick();
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
    setTimeout(function() {
      initActivityListByPage(pageNext, "none", null);
    }, 100);
  }
  
  $(document).ready(function() {
    //初始化类别信息
    initActivityType();
    initActivityByLike();
    initActivityByTop();
    //初始化技术专栏的信息
    var keyword = $("#keyword").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;");
    if(keyword.search('type')!=-1){
      var id = keyword.split('_')[1];
      initActivityListByPage(pageNext, id, id);
    }else{
      initActivityListByPage(pageNext, "none", null);
    }
  });
  
  var initActivityListByPage = function(pageNum, type_id, typename) {
    //查询出活动
    //获取关键字，表示查询所有符合的记录
    var params = "";
    var prarm = "";
    var typeStatus = false;
    if (type_id == "none") {
      var keyword = $("#keyword").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;");
      if (keyword == "请输入关键字词") {
        keyword = "";
        prarm = 'none'
      } else {
        prarm = '搜索关键字为<span class="text-info">#' + keyword + '#</span>的活动'
      }
      typeStatus = false;
      params = {
        pageSize : 8,
        page : pageNum,
        title : keyword,
        content : keyword,
        introduction : keyword,
        keyword : keyword,
        param : prarm,
        status : 1 //1 表示已发布
      };
    } else {
      typeStatus = true;
      pageNum = 1;
      params = {
        pageSize : 100,
        page : pageNum,
        'type.id' : type_id,
        param : '搜索类别为<span class="text-info">#' + typename + '#</span>的活动列表',
        status : 1 //1 表示已发布
      };
    }
  
    $.ajax({
      url : 'selectLikeActivityListByPage',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(data) {
        var activityList = '';
        var page = data.pageInfo;
        var data = data.activityList;
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
            }activityList += '<li style="animation-delay:0.' + i + 's" class="animated fadeInDown"><h3 class="blogtitle"><a target="_blank" href="find/' + id + '.html"  >'
            + data[i].title
            + '</a></h3><span class="blogpic imgscale"><a href="find/' + id + '.html" title=""><img src="' + data[i].images + '"  /></a></span><p class="blogtext">'
            + data[i].introduction
            + '</p><p class="bloginfo"><i class = "avatar"><img src="images/image_.jpg" border=0 width="30" height="30"></i><span>luotf</span><span><a href="javascript:void(0);">【'
            + keyword
            + '】</a></span><span class="m_time">'
            + Format(data[i].addtime, "yyyy-MM-dd")
            + '</span><span  class="clicknum">浏览('
            + data[i].clicknum
            + ')</span><span class="f_r"></p><a href="find/' + id + '.html" class="viewmore">阅读详情</a></span></li>'
          }
          var p = {
            client_id : 'cytzg9rLH',
            topic_source_id : parm
          };
          $.ajax({
            url : 'http://changyan.sohu.com/api/2/topic/count',
            type : 'get',
            data : p,
            dataType : 'jsonp',
            success : function(pl) {
              for (var i = 0; i < arr.length; i++) {
                $('.' + arr[i]).html(pl.result[arr[i]].comments);
              }
            },
            error : function() {
              layer.msg('出错啦', {
                icon : 2
              });
            }
          });
        } else {
            activityList = "<h1 style='font-size:110px;text-align:center;margin:20px;'>404</h1><h3 style='text-align:center;' class='font-bold'>抱歉，你所访问的资源不存在~</h3><h4 style='margin-bottom:110px;margin-top:55px;text-align:center;'><a style='background-color: #676a6c;padding: 5px 10px;color: #fff;border-radius: 10px;' href='index.jsp'>去首页</a></h4>";
        }
        if (page.pageNum >= 2) {
          $(".newblogs").find("ul").append(activityList);
        } else {
          $(".newblogs").find("ul").html(activityList);
        }
        if (page.total > 8 && !typeStatus) {
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
          topActivity +='<li><a href="find/' + id + '.html" title='+ data[i].title + ' target="_blank">'+data[i].title+'</a></li>';
        }
        // 初始化数据
        $(".notice").find("ul").html(topActivity);
        globalCount++;
      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
  };
  
  var pageNav = function(pageNum) {
    var type = "";
    type = $(".type_id").val();
    $(".type_id").val("");
    if (type == null || type == "") {
      type = "none";
    }
    var index = '';
    layer.ready(function() {
      index = layer.load(2, {
        shade : [ 0.1, '#eee' ] //0.1透明度的白色背景
      });
    });
  
    initActivityListByPage(pageNum, type, null);
    setTimeout(function() {
      window.scrollTo(0, 0); //滑动到浏览器顶部
      layer.close(index);
    }, 200);
  
  }
  
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
    }, 200);
  }
  
  //初始化所有地址信息
  var initActivityType = function() {
    //查询出活动地址
    //设置参数，表示查询所有的地址
    var params = {
      "data" : "all"
    };
    $.ajax({
      url : 'selectActivityType',
      type : 'post',
      data : params,
      dataType : 'json',
      success : function(data) {
        var typeName = '';
        for (var i = 0; i < data.length; i++) {
          var time = i * 0.03;
          typeName += '<a style="animation-delay:' + time + 's class="animated fadeIn" onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')" href="javascript:void(0);">' + data[i].typename + '</a> '
        }
        var length = '';
        var keyTitle = '';
        if (data.length > 5) {
          length = 5;
        } else {
          length = data.length;
        }
        for (var i = 0; i < length; i++) {
          keyTitle += '<a href="javascript:void(0);" onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')">' + data[i].typename + '</a>'
        }
  
        // 初始化数据
        $(".cloud").find('ul').html(typeName);
        $(".tagTitle").html(keyTitle);
      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
  }
  
  var searchType = function(type_id, typename) {
    $(".type_id").val(type_id);
    var index = '';
    layer.ready(function() {
      index = layer.load(2, {
        shade : [ 0.1, '#eee' ] //0.1透明度的白色背景
      });
    });
    initBlogListByPage(1, type_id, typename);
    setTimeout(function() {
      layer.close(index);
    }, 200);
  }
  
  //初始化推荐
  var initActivityByLike = function() {
    var params = {
      pageSize : 5,
      page : 1,
      isrecommend : 1 //1 表示推荐
    };
    $.ajax({
      url : 'selectGroupLikeActivityListByPage',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(data) {
        var likeActivity_1 = '';
        var likeActivity_2 = '';
        var data = data.activityList;
        var time = '';
  
        for (var i = 0; i < data.length; i++) {
          if (data[i].title.length > 25) {
            data[i].title = data[i].title.substring(0, 25) + "...";
          }
          var id = data[i].id.toString(8) * data[i].id;
          time = i * 0.03;
          if (i == 1) {
            likeActivity_1 = '<i ><a target="_blank" href="find/' + id + '.html"><img src="' + data[i].images + '"></i><p>' + data[i].title + '</p></a>'
          } else {
            likeActivity_2 += '<li style="animation-delay:' + time + 's class="animated fadeIn"> <i><img src="' + data[i].images + '"></i><p><a href="find/' + id + '.html">' + data[i].title + '</a></p><span>[' + Format(data[i].addtime, "yyyy-MM-dd") + ']</span> </li>';
          }
        }
        // 初始化数据
        $(".tuijian2").find(".tjpic").html(likeActivity_1);
        $(".tuijian2").find(".sidenews").html(likeActivity_2);
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
      pageSize : 9,
      page : 1,
      sort : "clickNum", //按点击量排序,默认按时间
    };
    $.ajax({
      url : 'selectGroupLikeActivityListByPage',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(data) {
        var clickActivity = '';
        var data = data.activityList;
  
        for (var i = 0; i < data.length; i++) {
          if (data[i].title.length > 20) {
            data[i].title = data[i].title.substring(0, 20) + "...";
          }
          var id = data[i].id.toString(8) * data[i].id;
          time = i * 0.05;
          clickActivity += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><i></i><a target="_blank" href="find/' + id + '.html">'
            + data[i].title
            + '</a></li>'
        }
        // 初始化数据
        $(".dj").find(".click").html(clickActivity);
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
      "S" : datetime.getMilliseconds() //毫秒   
    };
    if (/(y+)/.test(fmt))
      fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt))
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }
