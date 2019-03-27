package com.cms.controller.portal;

import com.cms.pojo.Activity;
import com.cms.service.ActivityService;
import com.cms.service.SubscribeService;
import com.cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangliyong on 2019/3/25.
 */
@Controller
@Slf4j
public class SubscribeController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscribeService subscribeService;

    @RequestMapping(value = "/saveSubscribeInformation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveSubscribeInformation(Integer userId , Integer activityId , String reminder_time) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(reminder_time);
        }catch (ParseException e){
            System.out.println(e);
        }
        int result = subscribeService.saveSubscribeInformation(userId,activityId,date);
        if(result > 0){
            map.put("code", 200);
        }else{
            //500表示：返回值为Null
            map.put("code", 500);
        }
        return map;
    }

    @RequestMapping(value = "/judgeIsSubscribe", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> judgeIsSubscribe(Integer userId , Integer activityId) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        boolean result = subscribeService.judgeIsSubscribe(userId,activityId);
        if(result){
            map.put("flag", false);
        }else{
            //500表示：返回值为Null
            map.put("flag", true);
        }
        return map;
    }
}
