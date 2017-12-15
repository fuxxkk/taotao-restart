package com.taotao.order.service;

import com.taotao.order.pojo.Order;

public interface OrderService {
    String saveOrder(Order order);

    Order queryOrderByOrderId(String orderId);
}
