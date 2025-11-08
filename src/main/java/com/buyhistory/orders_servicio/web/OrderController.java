package com.buyhistory.orders_servicio.web;

import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // tu frontend Vite
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable String id) {
        return service.findById(id);
    }
}
