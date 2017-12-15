package com.taotao.order.service.imp;

import com.taotao.order.mapper.OrderMapper;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String saveOrder(Order order) {
        String orderId = order.getUserId()+""+System.currentTimeMillis();

        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setStatus(1);
        order.setUpdateTime(new Date());

        orderMapper.saveOrder(order);

        return orderId;
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {

        return orderMapper.queryOrderByOrderId(orderId);
    }

    @Override
    public void autoCloseOrder() {
        orderMapper.autoCloseOrder(DateTime.now().minusDays(2).toDate());
        System.out.println("执行quartz..");
    }
}
