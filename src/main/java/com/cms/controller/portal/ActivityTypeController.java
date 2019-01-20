package com.cms.controller.portal;

import com.cms.pojo.ActivityType;
import com.cms.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/1/20.
 */
@Controller
public class ActivityTypeController {
    @Autowired
    private ActivityTypeService activityTypeService;

    @RequestMapping(value = "/selectActivityType", method = RequestMethod.POST)
    @ResponseBody
    public List<ActivityType> selectBlogType(@RequestParam(value = "data", required = false) String data) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data == "all") {
            map = null;
        }
        List<ActivityType> typeList = activityTypeService.selectActivityTypeListByPage(map);
        return typeList;
    }


}
