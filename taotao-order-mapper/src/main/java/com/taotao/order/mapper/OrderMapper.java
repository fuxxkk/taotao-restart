package com.taotao.order.mapper;

import com.taotao.order.pojo.Order;
import com.taotao.order.pojo.OrderItem;
import com.taotao.order.pojo.OrderShipping;

import java.util.Date;

public interface OrderMapper {

    void saveOrder(Order order);

   /* OrderItem queryOrderItemsByOrderId(String orderId);

    OrderShipping queryOrderShippingByOrderId(String orderId);*/

    Order queryOrderByOrderId(String orderId);

    void autoCloseOrder(Date date);
}
