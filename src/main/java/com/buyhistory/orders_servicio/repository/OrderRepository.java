package com.buyhistory.orders_servicio.repository;

import com.buyhistory.orders_servicio.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerEmail(String customerEmail);
}
