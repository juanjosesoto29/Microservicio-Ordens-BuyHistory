package com.buyhistory.orders_servicio.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
