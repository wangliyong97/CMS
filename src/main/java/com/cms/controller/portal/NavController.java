package com.cms.controller.portal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangliyong on 2019/2/16.
 */
@Controller
public class NavController {

    /**
     * 页面导航初始化
     * @param nav
     * @return
     */
    @RequestMapping("/{nav}")
    public String returnToJsp(@PathVariable String nav){
        String result="page/"+nav;
        return result;
    }

    /**
     * 导航栏搜索控制
     * @param keyboard
     * @param model
     * @return
     */
    @RequestMapping(value="/result")
    public String searchResult(String keyboard,Model model){
        if(keyboard != null){
            model.addAttribute("keyword", keyboard);
        }
        return "/page/result";
    }
}
