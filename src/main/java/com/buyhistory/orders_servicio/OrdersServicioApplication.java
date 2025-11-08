package com.buyhistory.orders_servicio;

import com.buyhistory.orders_servicio.model.Order;
import com.buyhistory.orders_servicio.model.OrderItem;
import com.buyhistory.orders_servicio.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
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
                        .total(170000)
                        .status("CREATED")
                        .createdAt(LocalDateTime.now().minusDays(1))
                        .items(List.of(
                                OrderItem.builder()
                                        .productId(101)
                                        .productName("Sombrero de Napoleón")
                                        .quantity(1)
                                        .price(120000)
                                        .build(),
                                OrderItem.builder()
                                        .productId(107)
                                        .productName("Calendario Maya")
                                        .quantity(1)
                                        .price(50000)
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
