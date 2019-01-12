package com.cms.filter;

import com.cms.service.BlackIpService;
import com.cms.util.UserIpUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.cms.pojo.BlackIp;

/**
 * Created by wangliyong on 2019/1/12.
 */
public class IpFilter implements Filter {
    private ApplicationContext applicationContext = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        //获取到用户的IP
        String ip= UserIpUtil.getIp(request);
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        BlackIp blackIp = null;
        BlackIpService blackIpServiceImpl = null;
        applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        blackIpServiceImpl = (BlackIpService) applicationContext
                .getBean("blackIpServiceImpl");
        blackIp=blackIpServiceImpl.selectBlackIpByIp(ip);
        if(blackIp==null){
            arg2.doFilter(request, response);
            return;
        }else{
            request.getRequestDispatcher("/error/blackIp.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
