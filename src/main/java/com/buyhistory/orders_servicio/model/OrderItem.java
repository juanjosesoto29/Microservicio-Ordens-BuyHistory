package com.buyhistory.orders_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String id;       // opcional, Mongo le puede poner un id embebido
    private Long productId;
    private String productName;
    private Integer quantity;
    private Long price;      // precio unitario
}
