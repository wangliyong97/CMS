package com.cms.controller.backend;

import com.cms.annotation.AccessLimit;
import com.cms.annotation.SystemLog;
import com.cms.pojo.Activity;
import com.cms.pojo.ActivityType;
import com.cms.pojo.BackendUser;
import com.cms.service.ActivityService;
import com.cms.service.ActivityTypeService;
import com.cms.service.BackendUserService;
import com.cms.util.ConstantUtil;
import com.cms.util.subStringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/25.
 */
@Controller
@RequestMapping(value = "/admin")
public class ActivityManagerController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityTypeService activityTypeService;

    @Autowired
    private BackendUserService backendUserService;

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ACTIVITY_ADD, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> addActivity(String prarm, Activity activity) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        // 将中文的分号转换成英文的分号
        if (activity.getKeyword() != null && activity.getKeyword() != "") {
            activity.setKeyword(subStringUtil.subKeyword(activity.getKeyword()));
        }
        activity.setAddtime(new Date());
        if (activityService.insertActivity(activity) != 0) {
            map.put("status", 200);
        } else {
            // 0表示：插入失败
            map.put("status", 0);
        }
        return map;
    }

    @RequestMapping(value = "/selectActivityType", method = RequestMethod.POST)
    @ResponseBody
    public List<ActivityType> selectActivityType(@RequestParam(value = "data", required = false) String data) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data == "all") {
            map = null;
        }
        List<ActivityType> typeList = activityTypeService.selectActivityTypeListByPage(map);
        return typeList;
    }

    @RequestMapping(value = "/selectGroupLikeActivityListByPage")
    @ResponseBody
    @AccessLimit(seconds = 1, maxCount = 15)
    public Map<String, Object> selectGroupLikeActivityListByPage(Activity activity,
            @RequestParam(value = "sort", required = true, defaultValue = "addTime") String sort,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sort", sort);
        if (activity.getTitle() != null && activity.getTitle() != "") {
            map.put("title", activity.getTitle());
        }
        if (activity.getIntroduction() != null && activity.getIntroduction() != "") {
            map.put("introduction", activity.getIntroduction());
        }
        if (activity.getKeyword() != null && activity.getKeyword() != "") {
            map.put("keyword", activity.getKeyword());
        }
        if (activity.getContent() != null && activity.getContent() != "") {
            map.put("content", activity.getContent());
        }
        if (activity.getIstop() != null) {
            map.put("isTop", activity.getIstop());
        }
        if (activity.getType() != null) {
            map.put("type_id", activity.getType().getId());
        }
        if (activity.getStatus() != null) {
            map.put("status", activity.getStatus());
        }
        if (activity.getIsrecommend() != null) {
            map.put("isRecommend", activity.getIsrecommend());
        }
        if (activity.getAddtime() != null) {
            map.put("addTime", activity.getAddtime());
        }
        // 分页显示：第1页开始，每页显示10条记录
        PageHelper.startPage(page, pageSize);
        List<Activity> activityList = activityService.selectGroupLikeActivityListByPage(map);
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(activityList);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (activityList.size() > 0) {
            returnMap.put("status", 200);
        } else {
            // 500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("activityList", activityList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    @RequestMapping(value = "/selectActivityTypeListByPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectActivityTypeListByPage(ActivityType activityType,
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (activityType.getTypename() != null && activityType.getTypename() != "") {
            map.put("typeName", activityType.getTypename());
        }
        if (activityType.getAddTime() != null) {
            map.put("addTime", activityType.getAddTime());
        }
        // 分页显示：第1页开始，每页显示10条记录
        PageHelper.startPage(page, pageSize);
        List<ActivityType> activityTypeList = activityTypeService.selectActivityTypeListByPage(map);
        PageInfo<ActivityType> pageInfo = new PageInfo<ActivityType>(activityTypeList);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (activityTypeList.size() > 0) {
            returnMap.put("status", 200);
        } else {
            // 500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("activityTypeList", activityTypeList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    @RequestMapping(value = "/selectActivityTypeById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectActivityType(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ActivityType activityType = activityTypeService.selectActivityTypeById(id);
        if (activityType != null) {
            map.put("status", 200);
        } else {
            map.put("status", 0);
        }
        map.put("activityType", activityType);
        return map;
    }

    @RequestMapping(value = "/selectActivityListByStatus", method = RequestMethod.POST)
    @ResponseBody
    @AccessLimit(seconds = 1, maxCount = 10)
    public Map<String, Object> selectActivityListByStatus() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list = activityService.selectActivityListByStatus();
        if (list.size() > 0) {
            map.put("status", 200);
        } else {
            // 500表示：返回值为Null
            map.put("status", 500);
        }
        map.put("list", list);
        return map;
    }

    @RequestMapping(value = "/selectActivityById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectActivityById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Activity activity = activityService.selectActivityById(id);
        if (activity != null) {
            map.put("status", 200);
        } else {
            // 500表示：返回值为Null
            map.put("status", 500);
        }
        map.put("activity", activity);
        return map;
    }

    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ACTIVITY_UPDATE, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> updateActivity(String prarm, Activity activity,HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = (String)session.getAttribute("username");
        BackendUser backendUser = backendUserService.findUserByLoginName(username);
        if(backendUser.getHaspermission() == 0){
            map.put("status", 0);
            map.put("msg", "没有更新权限");
            return map;
        }
        // 将中文的分号转换成英文的分号
        if (activity.getKeyword() != null && activity.getKeyword() != "") {
            activity.setKeyword(subStringUtil.subKeyword(activity.getKeyword()));
        }

        if (activityService.updateActivitySelective(activity) != 0) {
            map.put("status", 200);
            map.put("msg", "更新成功");
        } else {
            // 0表示：更新失败
            map.put("status", 0);
            map.put("msg", "更新失败");
        }
        return map;
    }

    @RequestMapping(value = "/updateActivityType", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ActivityType_UPDATE, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> updateActivityType(String prarm, HttpSession session, ActivityType activityType) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = (String) session.getAttribute("username");
        BackendUser backendUser = backendUserService.findUserByLoginName(username);
        if (backendUser.getHaspermission() == 0) {
            map.put("status", 0);
            map.put("msg", "没有更新权限");
            return map;
        }
        if (activityTypeService.selectActivityTypeByName(activityType.getTypename()) != null) {
            // 已经存在该类别
            map.put("status", 2);
        } else {
            if (activityTypeService.updateActivityTypeSelective(activityType) != 0) {
                map.put("status", 200);
            } else {
                // 0表示：更新失败
                map.put("status", 0);
            }
        }
        return map;
    }

    @RequestMapping(value = "/deleteActivityType", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ActivityType_DELETE, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> deleteActivityType(String prarm, HttpSession session, ActivityType ActivityType) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = (String) session.getAttribute("username");
        BackendUser backendUser = backendUserService.findUserByLoginName(username);
        if (backendUser.getHaspermission() == 0) {
            map.put("status", 0);
            map.put("msg", "没有删除权限");
            return map;
        }
        // 查询该类别下是否有活动
        if (activityTypeService.selectActivityTypeById(ActivityType.getId()).getNum() != 0) {
            // 该类别下有活动  不能删除
            map.put("status", 2);
        } else {
            if ((activityTypeService.deleteActivityTypeById(ActivityType.getId()) != 0)) {
                map.put("status", 200);
            } else {
                map.put("status", 0);
            }
        }
        return map;
    }

    @RequestMapping(value = "/addActivityType", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ACTIVITYTYPE_ADD, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> addActivityType(String prarm, ActivityType activityType) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (activityTypeService.selectActivityTypeByName(activityType.getTypename()) != null) {
            // 已经存在该类别
            map.put("status", 2);
        } else {
            activityType.setAddTime(new Date());
            if ((activityTypeService.insertActivityType(activityType)) != 0) {
                map.put("status", 200);
            } else {
                map.put("status", 0);
            }
        }
        return map;
    }
}
