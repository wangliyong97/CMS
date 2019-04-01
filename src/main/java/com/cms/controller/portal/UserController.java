package com.cms.controller.portal;
import com.cms.annotation.SystemLog;
import com.cms.pojo.User;
import com.cms.service.UserService;
import com.cms.util.CipherUtil;
import com.cms.util.ConstantUtil;
import com.cms.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangliyong on 2019/2/20.
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.LOGIN_IN,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> login(String username,String password,HttpSession session,Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        //取密码，并用MD5加密
        password = CipherUtil.generatePassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()){//使用shiro来验证
                token.setRememberMe(true);  //记住密码
                currentUser.login(token);//验证角色和权限
            }
            User user = userService.findUserByLoginName(username);
            map.put("code",200);
            map.put("msg","index");  //验证成功
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", username);
            session.setAttribute("avatar", user.getPicturePath());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("intro", user.getIntroduce());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("gender", user.getGender());
            session.setAttribute("birthday", user.getBirthday());
        } catch (Exception e) {
            map.put("code",0);
            map.put("msg","login"); //验证失败
            map.put("error","用户名或密码错误！");
            model.addAttribute("message", "用户名或密码错误");
        }
        return map;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.REGISTER,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> register(HttpServletRequest request,
                                        @RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        @RequestParam("nickname") String nickname,
                                        @RequestParam("email") String email,
                                        @RequestParam("intro") String intro) {
        Map<String, Object> map = new HashMap<String, Object>();
        handleNameCheck(request, username);
        if(password != null && !"".equals(password)){
            User user = new User(username,password,email,0,nickname,intro,1);
            int result = userService.insert(user);
            if(result < 0){
                map.put("code",0);
                map.put("msg","注册失败！");
            }else {
                map.put("code",200);
            }
        }
        return map;
    }

    /**
     * 退出登录状态
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    @SystemLog(description = ConstantUtil.LOGIN_OUT,userType=ConstantUtil.USERTYPE_USER)
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/";
    }

    /**
     * 修改用户名
     */
    @RequestMapping(value = "/item=name", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.MODIFYUSERNAME,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> modifyUsername(HttpServletRequest request,
                                     @RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "username") String newUserName) {
        Map<String, Object> map = new HashMap<String, Object>();
        handleNameCheck(request, newUserName);
        int result = userService.updateUsernameById(userId, newUserName);
        if (result < 0){
            map.put("msg","更新失败！");
        }else {
            map.put("code",0);
            // 更新session信息
            HttpSession session = request.getSession();
            session.setAttribute("username", newUserName);
        }
        return map;
    }

    /**
     * 修改邮箱
     */
    @RequestMapping(value = "/item=email", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.MODIFYEMAIL,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> modifyEmail(HttpServletRequest request,
                                              @RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "email") String newEmail) {
        Map<String, Object> map = new HashMap<String, Object>();
        handleNameCheck(request, newEmail);
        int result = userService.updateEmailById(userId, newEmail);
        if (result < 0){
            map.put("msg","更新失败！");
        }else {
            map.put("code",0);
            // 更新session信息
            HttpSession session = request.getSession();
            session.setAttribute("email", newEmail);
        }
        return map;
    }

    /**
     * 修改昵称
     */
    @RequestMapping(value = "/item=nickname", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.MODIFYNICKNAME,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> modifyNickname(HttpServletRequest request,
                                           @RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "nickname") String newNickname) {
        Map<String, Object> map = new HashMap<String, Object>();
        handleNameCheck(request, newNickname);
        int result = userService.updateNicknameById(userId, newNickname);
        if (result < 0){
            map.put("msg","更新失败！");
        }else {
            map.put("code",0);
            // 更新session信息
            HttpSession session = request.getSession();
            session.setAttribute("nickname", newNickname);
        }
        return map;
    }

    /**
     * 修改用户头像
     * 将Base64位编码的图片进行解码，并保存到指定目录
     */
    @RequestMapping(value = "/changeAvatar", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.MODIFYAVATAR,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> iconImageUpload(String image,@RequestParam(value = "userId") Integer userId , HttpSession session) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        String filePath = "d:\\upload\\avatar\\";
        String imagePath = UUID.randomUUID().toString()+".png";
        String avatarPath = "http://localhost:8080/upload/avatar/"+imagePath;
        //图片相对路径
        decodeBase64DataURLToImage(image.toString(),filePath + imagePath);
        int result = userService.updateAvatarById(userId,avatarPath);
        if(result < 0 ){
            map.put("msg","更新失败!");
        }else{
            map.put("code",200);
            map.put("msg","更新成功!");  //更新成功
            session.setAttribute("avatar", avatarPath);
        }
        return map;
    }

    //base64字符串转化成图片
    public static boolean decodeBase64DataURLToImage(String imgStr,String imgFilePath)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            imgStr = imgStr.split(",")[1];
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 修改用户个人信息
     */
    @RequestMapping(value = "/item=profile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyUsernameInformation(HttpServletRequest request,
                                              @RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "intro") String introduction,
                                              @RequestParam(value = "birthday") String birthday,
                                              @RequestParam(value = "gender") Integer gender) {
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        }catch (ParseException e){
            System.out.println(e);
        }
        int result = userService.updateUserProfileById(userId, introduction,date,gender);
        if (result < 0){
            map.put("msg","更新失败！");
        }else {
            map.put("code",0);
            // 更新session信息
            HttpSession session = request.getSession();
            session.setAttribute("intro", introduction);
            session.setAttribute("birthday", birthday);
            session.setAttribute("gender", gender);
        }
        return map;
    }

    /**
     * 修改账号密码
     */
    @RequestMapping(value = "/item=password", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = ConstantUtil.MODIFYPASSWORD,userType=ConstantUtil.USERTYPE_USER)
    public Map<String, Object> modifyPassword(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "newPwd") String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = userService.updateAccountPasswordById(userId, newPassword);
        if (result < 0){
            map.put("msg","密码修改失败！");
        }else {
            map.put("code",200);
        }
        return map;
    }

    /**
     * 检查用户名是否存在
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkUsernameUsed(HttpServletRequest request, @RequestParam("username") String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        handleNameCheck(request, username);
        User user = userService.findUserByLoginName(username);
        if (user != null) {
            map.put("code",200);
        } else {
            map.put("code",0);
        }
        return map;
    }

    /**
     * 检查电话号码是否存在
     */
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkProfileExist(HttpServletRequest request, @RequestParam("email") String email) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserByEmail(email);
        if (user != null) {
            map.put("code",200);
        } else {
            map.put("code",0);
        }
        return map;
    }

    // 检查用户名合法性
    private void handleNameCheck(HttpServletRequest request, String userName) {
        if (StringUtil.isEmpty_(userName) || !userService.checkUserName(userName)) ;
    }

    /**邮箱发送验证码
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendEmailCode(@RequestParam(value = "email") String email, @RequestParam(value = "code") String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.sendEmailCode(email,code)) {
            map.put("code",200);
            map.put("msg","发送成功!");
        } else {
            map.put("code",0);
            map.put("msg","发送失败!");
        }
        return map;
    }
}
