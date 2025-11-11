package com.buyhistory.orders_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private Instant createdAt;

    private String customerName;
    private String customerEmail;

    private String addressStreet;
    private String addressDetail;
    private String region;
    private String city;
    private String notes;

    private Long total;          // total en pesos
    private String status;       // p.ej. "CREATED"

    private List<OrderItem> items;
}
