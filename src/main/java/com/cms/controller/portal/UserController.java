package com.cms.controller.portal;
import com.cms.annotation.SystemLog;
import com.cms.pojo.User;
import com.cms.service.UserService;
import com.cms.util.CipherUtil;
import com.cms.util.ConstantUtil;
import com.cms.util.StringUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String result = "index";
        return result;
    }

    /**
     * 修改用户名
     */
    @RequestMapping(value = "/item=name", method = RequestMethod.POST)
    @ResponseBody
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
     * 修改邮箱
     */
    @RequestMapping(value = "/item=nickname", method = RequestMethod.POST)
    @ResponseBody
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


}
