package com.cms.controller.backend;

import com.cms.annotation.AccessLimit;
import com.cms.pojo.Visit;
import com.cms.service.VisitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * Created by wangliyong on 2019/2/7.
 */
@Controller
@RequestMapping(value = "/admin")
public class VisitManagerController {
    @Autowired
    private VisitService visitService;

    /**
     * 查询一定日期内的访客
     * @param format
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectVisitListByDate",method = RequestMethod.POST)
    @ResponseBody
    @AccessLimit(seconds=1,maxCount=10)
    public Map<String, Object> selectVisitListByDate(@RequestParam(value="format") String format, @RequestParam(value="startTime") String startTime, @RequestParam(value="endTime") String endTime) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(format!=""&&format!=null){
            map.put("format", format);
        }
        if(startTime!=""&&startTime!=null){
            map.put("startTime", startTime);
        }
        if(endTime!=""&&endTime!=null){
            map.put("endTime", endTime);
        }
        List<?> list=visitService.selectVisitListByDate(map);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(list.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("list", list);
        return returnMap;
    }

    /**
     * 通过Ip获取用户信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectVisitListByIp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectVisitListByIp() throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        List<?> list=visitService.selectVisitListByIp(map);
        if(list.size()>0){
            map.put("status", 200);
        }else{
            //500表示：返回值为Null
            map.put("status", 500);
        }
        map.put("list", list);
        return map;
    }

    /**
     * 模糊组合分页查询访客信息
     * @param visit
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectLikeVisitListByPage")
    @ResponseBody
    public Map<String, Object> selectLikeVisitListByPage(Visit visit,@RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime,@RequestParam(value="page", required=true,defaultValue="1") Integer page,@RequestParam(value="pageSize", required=true,defaultValue="9") Integer pageSize) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(startTime!=""&&startTime!=null){
            map.put("startTime", startTime);
        }
        if(endTime!=""&&endTime!=null){
            map.put("endTime", endTime);
        }
        if(visit.getIp()!=null&&visit.getIp()!=""){
            map.put("ip", visit.getIp());
        }
        if(visit.getCity()!=null&&visit.getCity()!=""){
            map.put("city", visit.getCity());
        }
        if(visit.getPlatformtype()!=null&&visit.getPlatformtype()!=""){
            map.put("platformType", visit.getPlatformtype());
        }
        if(visit.getBrowsertype()!=null&&visit.getBrowsertype()!=""){
            map.put("browserType", visit.getBrowsertype());
        }
        //分页显示：第1页开始，每页显示9条记录
        PageHelper.startPage(page, pageSize);
        List<Visit> visitList=visitService.selectLikeVisitListByPage(map);
        PageInfo<Visit> pageInfo=new PageInfo<Visit>(visitList);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(visitList.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("visitList", visitList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    /**
     * 模糊组合分页查询访客信息
     * @param visit
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectLikeVisitListGroupByIp")
    @ResponseBody
    public Map<String, Object> selectLikeVisitListGroupByIp(Visit visit,@RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime,@RequestParam(value="page", required=true,defaultValue="1") Integer page,@RequestParam(value="pageSize", required=true,defaultValue="9") Integer pageSize) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(startTime!=""&&startTime!=null){
            map.put("startTime", startTime);
        }
        if(endTime!=""&&endTime!=null){
            map.put("endTime", endTime);
        }
        if(visit.getIp()!=null&&visit.getIp()!=""){
            map.put("ip", visit.getIp());
        }
        if(visit.getCity()!=null&&visit.getCity()!=""){
            map.put("city", visit.getCity());
        }
        if(visit.getPlatformtype()!=null&&visit.getPlatformtype()!=""){
            map.put("platformType", visit.getPlatformtype());
        }
        if(visit.getBrowsertype()!=null&&visit.getBrowsertype()!=""){
            map.put("browserType", visit.getBrowsertype());
        }
        //分页显示：第1页开始，每页显示9条记录
        PageHelper.startPage(page, pageSize);
        List<?> visitList=visitService.selectLikeVisitListGroupByIp(map);
        PageInfo pageInfo=new PageInfo(visitList);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(visitList.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("visitList", visitList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    /**
     * 更新访客
     * @param visit
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateVisit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateVisit(Visit visit) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(visitService.updateByPrimaryKeySelective(visit)!=0){
            map.put("status", 200);
        }else{
            //0表示：更新失败
            map.put("status", 0);
        }
        return map;
    }
}
