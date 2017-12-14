package com.taotao.portal.interceptor;

import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderHandlerInterceptor implements HandlerInterceptor {
    @Value("${cookiename}")
    private   String COOKIE_NAME;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = CookieUtils.getCookieValue(request, COOKIE_NAME);
        User user = userService.queryByTicket(ticket);
        if (user != null) {
            request.setAttribute("user",user);
            return true;
        }
        String redirectUrl = request.getRequestURL().toString();
        response.sendRedirect(request.getContextPath()+"/page/login.html?redirectUrl="+redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
