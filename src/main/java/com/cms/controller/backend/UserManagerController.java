package com.cms.controller.backend;

import com.cms.annotation.SystemLog;
import com.cms.util.CipherUtil;
import com.cms.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
public class UserManagerController {
//    @Autowired
//    private IUserManagerService iUserManagerService;

    @RequestMapping("/login")
    public String tologin(HttpServletRequest request, HttpServletResponse response, Model model){
        return "login";
    }

    @RequestMapping("/checkLogin")
    @SystemLog(description = ConstantUtil.LOGIN_IN,userType=ConstantUtil.USERTYPE_ADMIN)
    public String login(String username,HttpSession session,String password,Model model) {
        String result = "";
        //取密码，并用MD5加密
        password = CipherUtil.generatePassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if (!currentUser.isAuthenticated()){//使用shiro来验证
                token.setRememberMe(true);  //记住密码
                currentUser.login(token);//验证角色和权限
            }
            result = "admin/index";//验证成功
            session.setAttribute("username", username);
        } catch (Exception e) {
            result = "login";//验证失败
            model.addAttribute("message", "用户名或密码错误");
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
