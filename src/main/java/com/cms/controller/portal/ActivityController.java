package com.cms.controller.portal;

import com.cms.annotation.AccessLimit;
import com.cms.annotation.SystemLog;
import com.cms.pojo.Activity;
import com.cms.service.ActivityService;
import com.cms.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/15.
 */
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @RequestMapping(value = "/selectGroupLikeActivityListByPage")
    @ResponseBody
    @AccessLimit(seconds=1,maxCount=15)
    public Map<String, Object> selectGroupLikeActivityListByPage(Activity activity, @RequestParam(value="sort", required=true,defaultValue="addTime") String sort, @RequestParam(value="page", required=true,defaultValue="1") Integer page, @RequestParam(value="pageSize", required=true,defaultValue="10") Integer pageSize) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sort", sort);
        if(activity.getTitle()!=null&&activity.getTitle()!=""){
            map.put("title", activity.getTitle());
        }
        if(activity.getIntroduction()!=null&&activity.getIntroduction()!=""){
            map.put("introduction", activity.getIntroduction());
        }
        if(activity.getKeyword()!=null&&activity.getKeyword()!=""){
            map.put("keyword", activity.getKeyword());
        }
        if(activity.getContent()!=null&&activity.getContent()!=""){
            map.put("content", activity.getContent());
        }
        if(activity.getIstop()!=null){
            map.put("isTop", activity.getIstop());
        }
        if(activity.getType()!=null){
            map.put("type_id", activity.getType().getId());
        }
        map.put("status", 1);
        if(activity.getStatus()!=null){
            map.put("status", activity.getStatus());
        }
        if(activity.getIsrecommend()!=null){
            map.put("isRecommend", activity.getIsrecommend());
        }
        if(activity.getAddtime()!=null){
            map.put("addTime", activity.getAddtime());
        }
        //分页显示：第1页开始，每页显示10条记录
        PageHelper.startPage(page, pageSize);
        List<Activity> activityList=activityService.selectGroupLikeActivityListByPage(map);
        PageInfo<Activity> pageInfo=new PageInfo<Activity>(activityList);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(activityList.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("activityList", activityList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    @RequestMapping(value = "/selectLikeActivityListByPage")
    @ResponseBody
    @AccessLimit(seconds=1,maxCount=15)
    @SystemLog(description = ConstantUtil.BLOG_FINDKEY,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> selectLikeBlogListByPage(String param,Activity activity,@RequestParam(value="sort", required=true,defaultValue="addTime") String sort,@RequestParam(value="page", required=true,defaultValue="1") Integer page,@RequestParam(value="pageSize", required=true,defaultValue="10") Integer pageSize) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("sort", sort);
        if(activity.getTitle()!=null&&activity.getTitle()!=""){
            map.put("title", activity.getTitle());
        }
        if(activity.getIntroduction()!=null&&activity.getIntroduction()!=""){
            map.put("introduction", activity.getIntroduction());
        }
        if(activity.getKeyword()!=null&&activity.getKeyword()!=""){
            map.put("keyword", activity.getKeyword());
        }
        if(activity.getContent()!=null&&activity.getContent()!=""){
            map.put("content", activity.getContent());
        }
        if(activity.getIstop()!=null){
            map.put("isTop", activity.getIstop());
        }
        if(activity.getType()!=null){
            map.put("type_id", activity.getType().getId());
        }
        map.put("status", 1);
        if(activity.getStatus()!=null){
            map.put("status", activity.getStatus());
        }
        if(activity.getIsrecommend()!=null){
            map.put("isRecommend", activity.getIsrecommend());
        }
        if(activity.getAddtime()!=null){
            map.put("addTime", activity.getAddtime());
        }
        //分页显示：第1页开始，每页显示10条记录
        PageHelper.startPage(page, pageSize);
        List<Activity> activityList=activityService.selectLikeActivityListByPageWithBlobs(map);
        PageInfo<Activity> pageInfo=new PageInfo<Activity>(activityList);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(activityList.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("activityList", activityList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    @RequestMapping(value = "/selectActivityByAllType")
    @ResponseBody
    public Map<String, Object> selectBlogByAllType() throws Exception{
        Map<String,List<Activity>> activityMap=activityService.selectActivityByAllType();
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(activityMap.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("activityMap", activityMap);
        return returnMap;
    }

}
