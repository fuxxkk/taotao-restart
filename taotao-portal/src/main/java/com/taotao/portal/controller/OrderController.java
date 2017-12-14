package com.taotao.portal.controller;

import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Value("${cookiename}")
    public  String COOKIE_NAME;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;


    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView toOrderCartPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("order-cart");
        String from = request.getHeader("Referer");
        try {
            //String ticket = CookieUtils.getCookieValue(request, COOKIE_NAME);
            User user = (User) request.getAttribute("user");
            List<Cart> cartList=null;
            if (user != null) {
                cartList   = cartService.queryListByUserId(user.getId());
            }
            mv.addObject("carts", cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
}
