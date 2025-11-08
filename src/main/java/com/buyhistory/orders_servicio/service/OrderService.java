package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> findAll();

    Order findById(String id);
}
