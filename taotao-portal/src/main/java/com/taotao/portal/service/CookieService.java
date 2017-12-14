package com.taotao.portal.service;

import com.taotao.cart.pojo.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CookieService {

    List<Cart> queryCartList(HttpServletRequest request) throws IOException;

    void addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception;

    void updateCart(Long itemId, Integer num,HttpServletRequest request,HttpServletResponse response) throws Exception;

    void deleteItem(Long itemId,HttpServletRequest request,HttpServletResponse response) throws  Exception;
}
