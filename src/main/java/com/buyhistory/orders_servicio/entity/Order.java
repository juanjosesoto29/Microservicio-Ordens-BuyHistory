package com.buyhistory.orders_servicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String customerName;
    private String customerEmail;

    // Dirección
    private String addressStreet;   // Calle
    private String addressDetail;   // Depto / casa
    private String region;          // Región
    private String city;            // Comuna

    @Column(length = 1000)
    private String notes;           // Notas de entrega

    private Double total;
    private String status;          // CREATED, PAID, etc.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}
