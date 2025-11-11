package com.buyhistory.orders_servicio;

import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.model.OrderItem;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class OrdersServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersServicioApplication.class, args);
    }

    @Bean
    CommandLineRunner initOrders(OrderRepository repo) {
        return args -> {
            if (repo.count() == 0) {

                Order demo = Order.builder()
                        .customerName("Cliente Demo")
                        .customerEmail("cliente.demo@example.com")
                        .addressStreet("Av. Siempre Viva 742")
                        .addressDetail("Depto 603")
                        .region("Región Metropolitana de Santiago")
                        .city("Santiago")
                        .notes("Entregar en conserjería")
                        .total(170000L)                           // Long
                        .status("CREATED")
                        // usamos Instant en el modelo
                        .createdAt(Instant.now().minus(1, ChronoUnit.DAYS))
                        .items(List.of(
                                OrderItem.builder()
                                        .productId(101L)         // Long
                                        .productName("Sombrero de Napoleón")
                                        .quantity(1)
                                        .price(120000L)          // Long
                                        .build(),
                                OrderItem.builder()
                                        .productId(107L)
                                        .productName("Calendario Maya")
                                        .quantity(1)
                                        .price(50000L)
                                        .build()
                        ))
                        .build();

                repo.save(demo);

                System.out.println("✅ Orden de ejemplo creada en MongoDB (buyhistory_orders.orders)");
            } else {
                System.out.println("ℹ️ La colección 'orders' ya tiene datos, no se crea seed.");
            }
        };
    }
}
