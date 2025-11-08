package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order create(Order order) {
        // Seteamos algunos campos por defecto
        order.setId(null);                        // que Mongo genere el _id
        if (order.getStatus() == null) {
            order.setStatus("CREATED");
        }
        order.setCreatedAt(LocalDateTime.now());

        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
