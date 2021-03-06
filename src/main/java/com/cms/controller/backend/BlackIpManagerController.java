package com.cms.controller.backend;

import com.cms.annotation.SystemLog;
import com.cms.pojo.BlackIp;
import com.cms.service.BlackIpService;
import com.cms.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliyong on 2019/2/7.
 */
@Controller
@RequestMapping(value = "/admin")
public class BlackIpManagerController {
    @Autowired
    private BlackIpService blackIpService;

    /**
     * 查询黑名单的数目
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectAllBlackIpCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectAllBlackIpCount() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Long count = blackIpService.selectAllBlackIpCount();
        if (count >= 0) {
            map.put("status", 200);
        } else {
            //0表示：更新失败
            map.put("status", 0);
        }
        map.put("count", count);
        return map;
    }

    /**
     * 通过页码查询黑名单
     * @param blackIp
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectLikeBlackIpListByPage")
    @ResponseBody
    public Map<String, Object> selectLikeBlackIpListByPage(BlackIp blackIp, @RequestParam(value="startTime") String startTime, @RequestParam(value="endTime") String endTime, @RequestParam(value="page", required=true,defaultValue="1") Integer page, @RequestParam(value="pageSize", required=true,defaultValue="9") Integer pageSize) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(startTime!=""&&startTime!=null){
            map.put("startTime", startTime);
        }
        if(endTime!=""&&endTime!=null){
            map.put("endTime", endTime);
        }
        if(blackIp.getIp()!=null&&blackIp.getIp()!=""){
            map.put("ip", blackIp.getIp());
        }
        if(blackIp.getCity()!=null&&blackIp.getCity()!=""){
            map.put("city", blackIp.getCity());
        }
        if(blackIp.getPlatformtype()!=null&&blackIp.getPlatformtype()!=""){
            map.put("platformType", blackIp.getPlatformtype());
        }
        if(blackIp.getBrowsertype()!=null&&blackIp.getBrowsertype()!=""){
            map.put("browserType", blackIp.getBrowsertype());
        }
        //分页显示：第1页开始，每页显示9条记录
        PageHelper.startPage(page, pageSize);
        List<BlackIp> blackIpList=blackIpService.selectLikeBlackIpListByPage(map);
        PageInfo<BlackIp> pageInfo=new PageInfo<BlackIp>(blackIpList);
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(blackIpList.size()>0){
            returnMap.put("status", 200);
        }else{
            //500表示：返回值为Null
            returnMap.put("status", 500);
        }
        returnMap.put("blackIpList", blackIpList);
        returnMap.put("pageInfo", pageInfo);
        return returnMap;
    }

    /**
     * 添加黑名单
     * @param prarm
     * @param blackIp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addBlackIp", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.BACKIP_ADD, userType = ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> addBlackIp(String prarm, BlackIp blackIp) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (blackIpService.selectBlackIpByIp(blackIp.getIp()) != null) {
            // 已经存在该IP
            map.put("status", 2);
        } else {
            blackIp.setTime(new Date());
            if (blackIpService.insert(blackIp) != 0) {
                map.put("status", 200);
            } else {
                //0表示：更新失败
                map.put("status", 0);
            }
        }
        return map;
    }

    /**
     * 更新黑名单
     * @param blackIp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateBlackIp",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBlackIp(BlackIp blackIp) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(blackIpService.updateByPrimaryKeySelective(blackIp)!=0){
            map.put("status", 200);
        }else{
            //0表示：更新失败
            map.put("status", 0);
        }
        return map;
    }

    /**
     * 删除黑名单
     * @param prarm
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteBlackIp",method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.BACKIP_DELETE,userType=ConstantUtil.USERTYPE_ADMIN)
    public Map<String, Object> deleteBlackIp(String prarm,Integer id) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        if(blackIpService.deleteByPrimaryKey(id)!=0){
            map.put("status", 200);
        }else{
            //0表示：失败
            map.put("status", 0);
        }
        return map;
    }
}
