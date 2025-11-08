package com.buyhistory.orders_servicio.repository;

import com.buyhistory.orders_servicio.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
