package com.buyhistory.orders_servicio.model;

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

    // Información del cliente
    @Column(nullable = false, length = 120)
    private String customerName;

    @Column(nullable = false, length = 120)
    private String customerEmail;

    // Dirección
    @Column(nullable = false, length = 200)
    private String addressStreet;

    @Column(length = 150)
    private String addressDetail;

    @Column(nullable = false, length = 120)
    private String region;

    @Column(nullable = false, length = 120)
    private String city;

    // Notas adicionales
    @Column(length = 500)
    private String notes;

    // Estado de la orden
    @Column(nullable = false, length = 20)
    private String status;  // CREATED, PAID, CANCELLED

    // Fecha de creación
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Total calculado
    @Column(nullable = false)
    private Double total;

    // Relación con items
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items;
}
