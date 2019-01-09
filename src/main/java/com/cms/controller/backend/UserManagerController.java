package com.cms.controller.backend;

import com.cms.service.IUserManagerService;
import com.cms.util.CipherUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangliyong on 2019/1/9.
 */

@Controller
@RequestMapping("/manager/")
public class UserManagerController {
    @Autowired
    private IUserManagerService iUserManagerService;

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String toLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model){
        return "login";
    }

    /**
     *
     * @param username
     * @param password
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("checkLogin")
    public String login(String username, String password, HttpSession httpSession, Model model){
        String result = "";
        //获取password后使用MD5进行加密
        password = CipherUtil.generatePassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();
        //使用shiro进行权限验证
        try {
            if(currentUser.isAuthenticated()){
                token.setRememberMe(true);
                currentUser.login(token);
            }
            result = "manage/index";//验证成功
            httpSession.setAttribute(username,username);
        } catch (Exception e) {
            result = "manage/login";
            model.addAttribute("message","用户名或密码错误!");
        }
        return result;
    }

    @RequestMapping("logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        String result = "login";
        return result;
    }

}
