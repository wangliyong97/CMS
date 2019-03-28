package com.cms.controller.portal;

import com.cms.annotation.AccessLimit;
import com.cms.annotation.SystemLog;
import com.cms.pojo.Activity;
import com.cms.pojo.Subscribe;
import com.cms.service.ActivityService;
import com.cms.service.SubscribeService;
import com.cms.service.UserService;
import com.cms.util.ConstantUtil;
import com.cms.vo.SubscribeActivityVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangliyong on 2019/3/28.
 */

@Controller
@Slf4j
public class SubscribeActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscribeService subscribeService;

    @RequestMapping(value = "/selectSubscribeActivityList")
    @ResponseBody
    @AccessLimit(seconds=1,maxCount=10)
    @SystemLog(description = ConstantUtil.ACTIVITY_SUBSCRIBE,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> selectSubscribeActivityList(Integer userId, @RequestParam(value="page", required=true,defaultValue="1") Integer page, @RequestParam(value="pageSize", required=true,defaultValue="10") Integer pageSize) throws Exception {
        Map<String, Object> returnMap=new HashMap<String, Object>();
        List<SubscribeActivityVo> subscribeActivityVos = new ArrayList<>();
        List<Subscribe> subActivityList = subscribeService.selectSubscribeActivityList(userId);
        if(subActivityList.size() > 0){
            Collections.sort(subActivityList, new Comparator<Subscribe>() {
                DateFormat dateFormat=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
                @Override
                public int compare(Subscribe o1, Subscribe o2) {
                    Date date1 = new Date();
                    Date date2 = new Date();
                    try{
                        date1 = dateFormat.parse(o1.getReminderTime().toString());
                        date2 = dateFormat.parse(o2.getReminderTime().toString());
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    return date1.compareTo(date2);
                }
            });
            for(Subscribe subActivity : subActivityList){
                Activity activity = activityService.selectActivityById(subActivity.getActivityId());
                if(activity != null){
                    SubscribeActivityVo subscribeActivityVo = new SubscribeActivityVo(subActivity.getActivityId(),activity.getTitle(),activity.getIntroduction(),
                            activity.getKeyword(),activity.getImages(),activity.getClicknum(),
                            activity.getCommentnum(),activity.getAgreenum(),activity.getIstop(),
                            activity.getIsrecommend(),activity.getUpdatetime(),activity.getAddtime(),
                            activity.getStatus(),activity.getType(),activity.getUser(),
                            activity.getContent(),subActivity.getUserId(),subActivity.getStatus(),
                            subActivity.getReminderTime());
                    subscribeActivityVos.add(subscribeActivityVo);
                }
            }
            //分页显示：第1页开始，每页显示10条记录
            PageHelper.startPage(page, pageSize);
            PageInfo<SubscribeActivityVo> pageInfo=new PageInfo<SubscribeActivityVo>(subscribeActivityVos);
            returnMap.put("pageInfo", pageInfo);
        }
        returnMap.put("subscribeActivityVos", subscribeActivityVos);
        return returnMap;
    }
}
