package com.cms.controller.portal;

import com.cms.annotation.AccessLimit;
import com.cms.annotation.SystemLog;
import com.cms.pojo.Activity;
import com.cms.pojo.User;
import com.cms.service.ActivityService;
import com.cms.service.UserService;
import com.cms.util.ActivityIdSafeUtil;
import com.cms.util.ConstantUtil;
import com.cms.util.subStringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @SystemLog(description = ConstantUtil.ACTIVITY_FINDKEY,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> selectLikeActivityListByPage(String param,Activity activity,@RequestParam(value="sort", required=true,defaultValue="addTime") String sort,@RequestParam(value="page", required=true,defaultValue="1") Integer page,@RequestParam(value="pageSize", required=true,defaultValue="10") Integer pageSize) throws Exception{
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
    public Map<String, Object> selectActivityByAllType() throws Exception{
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

    /**
     * 通过活动状态进行搜索
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/selectActivityListByStatus", method = RequestMethod.POST)
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

    /**
     *结合summernote实现图片上传
     * @param prarm
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/uploadActivityImages", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.UPLOAD_ACTIVITYIMAGES, userType = ConstantUtil.USERTYPE_USER)
    public Map<String, Object> uploadActivityImages(String prarm, HttpServletRequest request) throws Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, Object> map = new HashMap<String, Object>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            Iterator<String> fileNamesIter = mreq.getFileNames();
            while (fileNamesIter.hasNext()) {
                MultipartFile file = mreq.getFile(fileNamesIter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    if (myFileName.trim() != "") {
                        String fileName = file.getOriginalFilename();
                        String fileBaseName = fileName.substring(0, fileName.lastIndexOf("."));
                        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String newFileName = df.format(new Date());
                        String fileNames = newFileName + new Random().nextInt(1000000) + "." + fileExt;
                        String filePath = "d:\\upload\\activity\\" + newFileName + "\\" + fileNames;
                        File localFile = new File(filePath);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);
                        fileNames = "/upload/activity/" + newFileName + "/" + fileNames;
                        map.put("name", fileBaseName);
                        map.put("path", fileNames);
                        map.put("status", 200);
                    }
                }
            }
        }
        return map;
    }

    @RequestMapping("/getFileList")
    @ResponseBody
    protected Map<String, Object> CalculateGeoServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MalformedURLException {
        ArrayList<String> fileList=new ArrayList<String>();
        String params="d:\\upload\\background";
        fileList=getFiles(params,fileList);
        Map<String, Object> map=new HashMap<String, Object>();
        if(fileList.size()>0){
            map.put("status", 200);
        }else{
            map.put("status", 0);
        }
        map.put("fileList", fileList);
        return map;
    }

    /**
     * 通过递归得到某一路径下所有的目录及其文件
     * @param filePath 文件路径
     * @return
     */
    public static ArrayList<String> getFiles(String filePath, ArrayList<String> fileList) {
        ArrayList<String> fileListAll =fileList;
        File root = new File(filePath);
        File[] files = root.listFiles();
        String[] arr=new String[10];
        if(files!=null){
            for (File file : files) {
                if (file.isDirectory()) {
                    /*
                     * 递归调用
                     */
                    arr=file.getAbsolutePath().split(":");
                    getFiles(arr[1].replace("\\","/"),fileListAll);
                } else {
                    arr=file.getAbsolutePath().split(":");
                    fileList.add(arr[1].replace("\\","/"));
                }
            }
        }
        return fileListAll;
    }

    /**
     * 编辑活动
     * @param prarm
     * @param activity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/addActivity", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.ACTIVITY_ADD, userType = ConstantUtil.USERTYPE_USER)
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

    /**
     * 添加封面图片
     * @param prarm
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadBg", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.UPLOAD_IMAGES, userType = ConstantUtil.USERTYPE_USER)
    public Map<String, Object> uploadBg(String prarm, HttpServletRequest request) throws Exception {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String, Object> map = new HashMap<String, Object>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            Iterator<String> fileNamesIter = mreq.getFileNames();
            while (fileNamesIter.hasNext()) {
                MultipartFile file = mreq.getFile(fileNamesIter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    if (myFileName.trim() != "") {
                        String fileName = file.getOriginalFilename();
                        String fileBaseName = fileName.substring(0, fileName.lastIndexOf("."));
                        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String newFileName = df.format(new Date());
                        String fileNames = newFileName + new Random().nextInt(1000000) + "." + fileExt;
                        String filePath = "d:\\upload\\background\\" + fileNames;
                        File localFile = new File(filePath);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);
                        fileNames = "/upload/background/" + newFileName + "/" + fileNames;
                        map.put("name", fileBaseName);
                        map.put("path", fileNames);
                        map.put("status", 200);
                    }
                }
            }
        }
        return map;
    }
}
