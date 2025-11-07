package com.buyhistory.orders_servicio.repository;

import com.buyhistory.orders_servicio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
