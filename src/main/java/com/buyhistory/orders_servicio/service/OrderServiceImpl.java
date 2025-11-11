package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order create(Order order) {
        // Deja que Mongo genere el id
        order.setId(null);

        // Fecha de creación si no viene
        if (order.getCreatedAt() == null) {
            order.setCreatedAt(Instant.now());
        }

        // Estado por defecto
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("CREATED");
        }

        // Calcular total si vienen items
        if (order.getItems() != null && !order.getItems().isEmpty()) {
            long total = order.getItems().stream()
                    .mapToLong(it -> (long) it.getPrice() * it.getQuantity())
                    .sum();
            order.setTotal(total);
        }

        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
