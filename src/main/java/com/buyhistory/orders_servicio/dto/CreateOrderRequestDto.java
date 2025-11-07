package com.buyhistory.orders_servicio.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequestDto {

    private String customerName;
    private String customerEmail;

    private String addressStreet;
    private String addressDetail;
    private String region;
    private String city;
    private String notes;

    private List<OrderItemRequestDto> items;
}
