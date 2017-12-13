package com.taotao.cart.service;

import com.taotao.manage.pojo.Item;

public interface CartService {

    void addCart(Long userId, Item item, Integer num)throws Exception;
}
