package com.taotao.cart.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.pojo.Cart;
import com.taotao.cart.service.CartService;
import com.taotao.manage.pojo.Item;
import com.taotao.rediseService.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private RedisService redisService;

    private static final String REDIS_CART_KEY = "TT_CART";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void addCart(Long userId, Item item, Integer num) throws Exception {
        String key = REDIS_CART_KEY+userId;
        String field = item.getId().toString();
        String cartJson = redisService.hget(key, field);
        Cart cart = null;
        if (StringUtils.isNotEmpty(cartJson)) {  //购物车已存在商品
            cart = MAPPER.readValue(cartJson, Cart.class);
            cart.setNum(cart.getNum()+num);
            cart.setUpdated(new Date());
        }else {  //第一次放进购物车
            cart = new Cart();
            cart.setNum(num);
            cart.setUpdated(new Date());
            cart.setCreated(new Date());
            cart.setItemId(item.getId());
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());
            if(item.getImages()!=null){
                cart.setItemImage(item.getImages()[0]);
            }
            cart.setUserId(userId);
        }
        redisService.hset(key, field, MAPPER.writeValueAsString(cart));
    }
}
