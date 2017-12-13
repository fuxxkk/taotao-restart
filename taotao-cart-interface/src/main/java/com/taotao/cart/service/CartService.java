package com.taotao.cart.service;

import com.taotao.cart.pojo.Cart;
import com.taotao.manage.pojo.Item;

import java.util.List;

public interface CartService {

    void addCart(Long userId, Item item, Integer num)throws Exception;

    List<Cart> queryListByUserId(Long userId)throws Exception;
}
