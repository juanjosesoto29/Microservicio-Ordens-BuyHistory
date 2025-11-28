package com.buyhistory.orders_servicio.web;

import com.buyhistory.orders_servicio.dto.CreateOrderRequestDto;
import com.buyhistory.orders_servicio.dto.OrderResponseDto;
import com.buyhistory.orders_servicio.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders") 
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Tag(name = "Órdenes", description = "Operaciones CRUD del microservicio de órdenes de BuyHistory")
public class OrderController {

    private final OrderService orderService;

    // ===========================
    // Crear orden
    // ===========================

    @Operation(
            summary = "Crear una nueva orden",
            description = "Recibe los datos del carrito y del cliente para generar una orden completa en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Orden creada correctamente")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody CreateOrderRequestDto requestDto
    ) {
        OrderResponseDto created = orderService.createOrder(requestDto);
        return ResponseEntity
                .created(URI.create("/api/v1/orders/" + created.getId()))
                .body(created);
    }

    // ===========================
    // Obtener todas las órdenes
    // ===========================

    @Operation(
            summary = "Obtener todas las órdenes",
            description = "Retorna una lista completa de todas las órdenes registradas."
    )
    @ApiResponse(responseCode = "200", description = "Órdenes obtenidas correctamente")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // ===========================
    // Obtener orden por ID
    // ===========================

    @Operation(
            summary = "Obtener una orden por ID",
            description = "Busca una orden específica utilizando su ID único."
    )
    @ApiResponse(responseCode = "200", description = "Orden obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        OrderResponseDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // ===========================
    // Obtener órdenes por email del cliente
    // ===========================

    @Operation(
            summary = "Buscar órdenes por email",
            description = "Busca todas las órdenes asociadas al email del cliente."
    )
    @ApiResponse(responseCode = "200", description = "Órdenes obtenidas correctamente")
    @GetMapping("/customer/{email}")
    public ResponseEntity<List<OrderResponseDto>> getByCustomerEmail(@PathVariable String email) {
        List<OrderResponseDto> orders = orderService.getOrdersByCustomerEmail(email);
        return ResponseEntity.ok(orders);
    }

    // ===========================
    // Actualizar estado de la orden
    // ===========================

    @Operation(
            summary = "Actualizar estado de la orden",
            description = "Permite actualizar el estado de la orden. Ejemplo: PAID, SHIPPED, COMPLETED."
    )
    @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente")
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        OrderResponseDto updated = orderService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // ===========================
    // Eliminar orden
    // ===========================

    @Operation(
            summary = "Eliminar una orden",
            description = "Elimina una orden del sistema utilizando su ID."
    )
    @ApiResponse(responseCode = "204", description = "Orden eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
