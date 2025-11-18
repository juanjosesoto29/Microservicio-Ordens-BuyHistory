package com.buyhistory.orders_servicio.web;

import com.buyhistory.orders_servicio.dto.CreateOrderRequestDto;
import com.buyhistory.orders_servicio.dto.OrderResponseDto;
import com.buyhistory.orders_servicio.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")   // 🔥 importante para el frontend
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    // Crear orden
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        OrderResponseDto created = orderService.createOrder(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/orders/" + created.getId()))
                .body(created);
    }

    // Obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Obtener orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        OrderResponseDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Obtener órdenes por email de cliente
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<OrderResponseDto>> getByCustomerEmail(@PathVariable String email) {
        List<OrderResponseDto> orders = orderService.getOrdersByCustomerEmail(email);
        return ResponseEntity.ok(orders);
    }

    // Actualizar estado de la orden
    // Ejemplo de llamada: PUT /api/v1/orders/1/status?status=PAID
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        OrderResponseDto updated = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // Eliminar orden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order created = service.create(order);
        return ResponseEntity
                .created(URI.create("/api/v1/orders/" + created.getId()))
                .body(created);
    }
}
