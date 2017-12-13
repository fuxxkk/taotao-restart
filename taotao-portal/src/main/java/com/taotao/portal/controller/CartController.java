package com.taotao.portal.controller;

import com.taotao.cart.service.CartService;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://www.taotao.com/cart/商品ID/购买数量.html
@Controller
@RequestMapping("/cart")
public class CartController {

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
            String ticket = CookieUtils.getCookieValue(request, UserController.COOKIE_NAME);
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

}
