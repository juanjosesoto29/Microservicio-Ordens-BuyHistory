package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.dto.CreateOrderRequestDto;
import com.buyhistory.orders_servicio.dto.OrderItemRequestDto;
import com.buyhistory.orders_servicio.dto.OrderItemResponseDto;
import com.buyhistory.orders_servicio.dto.OrderResponseDto;
import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.model.OrderItem;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {

        Order order = Order.builder()
                .customerName(requestDto.getCustomerName())
                .customerEmail(requestDto.getCustomerEmail())
                .addressStreet(requestDto.getAddressStreet())
                .addressDetail(requestDto.getAddressDetail())
                .region(requestDto.getRegion())
                .city(requestDto.getCity())
                .notes(requestDto.getNotes())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();

        // Mapear items desde el DTO
        List<OrderItem> items = requestDto.getItems().stream()
                .map(this::mapToEntity)
                .toList();

        // Relación bidireccional
        items.forEach(item -> item.setOrder(order));

        // Calcular total
        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setItems(items);
        order.setTotal(total);

        Order saved = orderRepository.save(order);

        return mapToResponse(saved);
    }

    private OrderItem mapToEntity(OrderItemRequestDto dto) {
        return OrderItem.builder()
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }

    private OrderResponseDto mapToResponse(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(this::mapItemToResponse)
                .toList();

        return OrderResponseDto.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .addressStreet(order.getAddressStreet())
                .addressDetail(order.getAddressDetail())
                .region(order.getRegion())
                .city(order.getCity())
                .notes(order.getNotes())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .total(order.getTotal())
                .items(itemDtos)
                .build();
    }

    private OrderItemResponseDto mapItemToResponse(OrderItem item) {
        double subtotal = item.getPrice() * item.getQuantity();

        return OrderItemResponseDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .subtotal(subtotal)
                .build();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));

        return mapToResponse(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<OrderResponseDto> getOrdersByCustomerEmail(String email) {
        return orderRepository.findByCustomerEmail(email).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrderResponseDto updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));

        order.setStatus(status);
        Order updated = orderRepository.save(order);

        return mapToResponse(updated);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
