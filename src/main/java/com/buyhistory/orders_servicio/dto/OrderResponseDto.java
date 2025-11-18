package com.buyhistory.orders_servicio.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private String customerName;
    private String customerEmail;

    private String addressStreet;
    private String addressDetail;
    private String region;
    private String city;

    private String notes;

    private String status;
    private LocalDateTime createdAt;
    private Double total;

    private List<OrderItemResponseDto> items;
}
