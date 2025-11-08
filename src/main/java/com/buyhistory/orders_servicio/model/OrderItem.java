package com.buyhistory.orders_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Integer productId;
    private String productName;
    private Integer quantity;
    private Integer price;
}
