package com.taotao.portal.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import com.taotao.portal.service.CookieService;
import com.taotao.portal.util.CookieUtils;
import com.taotao.rediseService.RedisService;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CookieServiceImp implements CookieService {

    private static final String COOKIE_CART_NAME = "tt_cart";
    private static final int COOKIE_CART_MAX_AGE=3600*7;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String REDIS_CART_KEY = "TT_CART";


    @Autowired
    private ItemService itemService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CartService cartService;

    @Override
    public List<Cart> queryCartList(HttpServletRequest request) throws IOException {

        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_CART_NAME, true);
        if (StringUtils.isNotEmpty(cookieValue)) {
            return MAPPER.readValue(cookieValue, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
        }

        return null;
    }

    @Override
    public void addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> carList = queryCartList(request);
        boolean isDeal = false; //是否处理商品
        if (carList != null&&carList.size()>0) {
            for (Cart cart :
                    carList) {
                if (itemId.equals(cart.getItemId())) {
                    cart.setNum(cart.getNum()+num);
                    isDeal=true;
                    break;
                }
            }
        }

        //没有要处理的商品,要新增
        if(!isDeal){
            if (carList == null) {
                carList = new ArrayList<>();
            }
            Item item = itemService.queryById(itemId);
            Cart cart = new Cart();
            cart.setNum(num);
            cart.setItemId(itemId);
            if (item.getImages() != null) {
                cart.setItemImage(item.getImages()[0]);
            }
            cart.setItemPrice(item.getPrice());
            cart.setCreated(new Date());
            cart.setUpdated(new Date());
            cart.setItemTitle(item.getTitle());
            carList.add(cart);
        }
        //保存到cookie
        CookieUtils.setCookie(request,response,COOKIE_CART_NAME,MAPPER.writeValueAsString(carList),COOKIE_CART_MAX_AGE,true);
    }

    @Override
    public void updateCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> cartList = queryCartList(request);
        if (cartList!=null&&cartList.size()>0) {
            for (Cart cart :
                    cartList) {
                if (itemId.equals(cart.getItemId())) {
                    cart.setNum(num);
                    cart.setUpdated(new Date());
                    break;
                }
            }
            CookieUtils.setCookie(request,response,COOKIE_CART_NAME,MAPPER.writeValueAsString(cartList),COOKIE_CART_MAX_AGE,true);
        }
    }

    @Override
    public void deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> cartList = queryCartList(request);
        if (cartList != null && cartList.size() > 0) {
            Iterator<Cart> iterator = cartList.iterator();
            while (iterator.hasNext()) {
                Cart cart = iterator.next();
                if (itemId.equals(cart.getItemId())) {
                    iterator.remove();
                }
            }

            CookieUtils.setCookie(request,response,COOKIE_CART_NAME,MAPPER.writeValueAsString(cartList),COOKIE_CART_MAX_AGE,true);
        }
    }

    @Override
    public void addCookieCart(HttpServletRequest request, Long userId) throws Exception {
        List<Cart> cartListCookie = queryCartList(request);
        List<Cart> cartListRedis = cartService.queryListByUserId(userId);


        List<Cart> result = null;
        if (cartListCookie != null && cartListCookie.size() > 0) {
            result = cartListCookie;
            if(cartListRedis !=null&&cartListRedis.size()>0) {
                int size = result.size();
                for (int i = 0; i< size; i++) {
                    for (int j = 0;j<cartListRedis.size();j++) {
                        if (result.get(i).getItemId().equals(cartListRedis.get(j).getItemId())) {
                            result.get(i).setNum(result.get(i).getNum()+cartListRedis.get(j).getNum());
                        }else {
                            result.add(cartListRedis.get(j));
                        }
                    }
                }
            }
        }else {
            if(cartListRedis !=null&&cartListRedis.size()>0) {
                result = cartListRedis;
            }
        }

        //保存到redis
        if (result != null && result.size() > 0) {
            for (Cart cart :
                    result) {
                redisService.hset(REDIS_CART_KEY+userId.toString(), cart.getItemId().toString(), MAPPER.writeValueAsString(cart));
            }
        }


    }

}
