package com.cms.controller.portal;

import com.cms.annotation.AccessLimit;
import com.cms.annotation.SystemLog;
import com.cms.pojo.Activity;
import com.cms.pojo.User;
import com.cms.service.ActivityService;
import com.cms.service.UserService;
import com.cms.util.ActivityIdSafeUtil;
import com.cms.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/find/{id}.html")
    @SystemLog(description = ConstantUtil.ACTIVITY_SELECT,userType=ConstantUtil.USERTYPE_USER)
    public String selectActivityById(@PathVariable Integer id, Model model) throws Exception {
        int sId= ActivityIdSafeUtil.ActivityIdToSafe(id);
        if(id==null||id<=0){
            //0表示查询 错误
            model.addAttribute("status", 0);
        }else{
            Activity activity=activityService.selectActivityUserById(sId);
            if(activity==null){
                //查询的博客不存在
                model.addAttribute("status", 500);
            }else{
                model.addAttribute("status", 200);
                User user = userService.selectUserById(activity.getUser().getId());
                model.addAttribute("user", user);
            }
            model.addAttribute("activity", activity);
        }
        return "page/info";
    }

    @RequestMapping(value = "/selectPrevActivity")
    @ResponseBody
    public Map<String, Object> selectPrevActivity(Integer id) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        Activity activity=activityService.selectPrevActivity(id);
        if(activity!=null){
            map.put("status", 200);
        }else{
            //500表示：返回值为Null
            map.put("status", 500);
        }
        map.put("activity", activity);
        return map;
    }

    @RequestMapping(value = "/selectNextActivity")
    @ResponseBody
    public Map<String, Object> selectNextActivity(Integer id) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        Activity activity=activityService.selectNextActivity(id);
        if(activity!=null){
            map.put("status", 200);
        }else{
            //500表示：返回值为Null
            map.put("status", 500);
        }
        map.put("activity", activity);
        return map;
    }

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
