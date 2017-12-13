package com.taotao.portal.controller;

import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//http://www.taotao.com/cart/商品ID/购买数量.html
@Controller
@RequestMapping("/cart")
public class CartController {
    @Value("${cookiename}")
    public  String COOKIE_NAME;

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/{itemId}/{num}",method = RequestMethod.GET)
    public String addCart(@PathVariable("itemId")Long itemId, @PathVariable("num")Integer num, HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            String ticket = CookieUtils.getCookieValue(request, COOKIE_NAME);
            User user = userService.queryByTicket(ticket);
            if (user != null) {  //已经登录,保存到redis
                Item item = itemService.queryById(itemId);
                cartService.addCart(user.getId(),item,num);
            } else {  //未登录,保存到cookie

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/cart/show.html";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("cart");

        try {
            String ticket = CookieUtils.getCookieValue(request, COOKIE_NAME);
            User user = userService.queryByTicket(ticket);
            List<Cart> cartList = null;
            if (user != null) {
                //已登录
                 cartList = cartService.queryListByUserId(user.getId());
            }else {
                //未登录

            }
            mv.addObject("cartList",cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
}
