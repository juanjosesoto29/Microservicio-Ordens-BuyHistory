package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.dto.CreateOrderRequestDto;
import com.buyhistory.orders_servicio.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(CreateOrderRequestDto request);

    List<OrderResponseDto> getAllOrders();
}
