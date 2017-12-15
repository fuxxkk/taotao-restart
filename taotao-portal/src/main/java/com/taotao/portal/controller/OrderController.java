package com.taotao.portal.controller;

import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.portal.service.CookieService;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Value("${cookiename}")
    public  String COOKIE_NAME;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView toOrderCartPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("order-cart");
        String from = request.getHeader("Referer");
        String str = "/page/login.html?redirect";
        try {
            User user = (User) request.getAttribute("user");
            //如果是未登录到登录,将cookie里面的商品放到redis里面
            if (from.contains(str)) {
                cookieService.addCookieCart(request,user.getId());
            }

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

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> submit(Order order,HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = new HashMap<>();
            map.put("status", 500);

            User user = (User) request.getAttribute("user");
            order.setBuyerNick(user.getUsername());
            order.setUserId(user.getId());
            String orderId = orderService.saveOrder(order);
            if (StringUtils.isNotEmpty(orderId)) {
                map.put("status", 200);
                map.put("data", orderId);
            }
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body(null);
    }

    @RequestMapping("/success")
    public ModelAndView toSuccessPage(@RequestParam("id")String orderId) {

        ModelAndView mv = new ModelAndView("success");
        try {
            Order order = orderService.queryOrderByOrderId(orderId);
            mv.addObject("order", order);
            mv.addObject("date", DateTime.now().plusDays(2).toString("MM月dd日"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;

    }
}


