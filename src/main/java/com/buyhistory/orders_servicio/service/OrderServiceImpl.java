package com.buyhistory.orders_servicio.service;

import com.buyhistory.orders_servicio.dto.*;
import com.buyhistory.orders_servicio.entity.Order;
import com.buyhistory.orders_servicio.entity.OrderItem;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(CreateOrderRequestDto request) {
        // Calcular total
        double total = request.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        // Crear entidad Order
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(total);
        order.setStatus("CREATED");

        // Dirección
        order.setAddressStreet(request.getAddressStreet());
        order.setAddressDetail(request.getAddressDetail());
        order.setRegion(request.getRegion());
        order.setCity(request.getCity());
        order.setNotes(request.getNotes());

        // Items
        List<OrderItem> items = request.getItems().stream()
                .map(i -> OrderItem.builder()
                        .productId(i.getProductId())
                        .productName(i.getProductName())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
                        .order(order)
                        .build())
                .toList();

        order.setItems(items);

        Order saved = orderRepository.save(order);
        return mapToDto(saved);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    // ==== helpers de mapeo ====

    private OrderResponseDto mapToDto(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getItems().stream()
                .map(this::mapItemToDto)
                .toList();

        return OrderResponseDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .addressStreet(order.getAddressStreet())
                .addressDetail(order.getAddressDetail())
                .region(order.getRegion())
                .city(order.getCity())
                .notes(order.getNotes())
                .total(order.getTotal())
                .status(order.getStatus())
                .items(itemDtos)
                .build();
    }

    private OrderItemResponseDto mapItemToDto(OrderItem item) {
        return OrderItemResponseDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }
}
