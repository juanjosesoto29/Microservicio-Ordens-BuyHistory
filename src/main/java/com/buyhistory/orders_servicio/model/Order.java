package com.buyhistory.orders_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;                     // Mongo usará un ObjectId en String

    // Datos del cliente (coinciden con el payload del checkout)
    private String customerName;
    private String customerEmail;

    private String addressStreet;
    private String addressDetail;
    private String region;
    private String city;
    private String notes;

    private Integer total;

    private String status;                // p.ej. "CREATED", "PAID", etc.

    private LocalDateTime createdAt;

    private List<OrderItem> items;
}
